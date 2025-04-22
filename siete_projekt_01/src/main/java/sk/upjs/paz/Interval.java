package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

/**
 * Closed interval of discrete long values with empty spaces in it. Imagine it
 * as an array of, for example, 10 bool values [1,1,0,0,0,1,1,0,1,0], it has min
 * = 0, max = 9, length = 10, full subintervals are <0,1>,<5,6>,<8,8> and empty
 * subintervals are <2,4>,<7,7> and <9,9>. After adding full subinterval <4,7>,
 * the empty subintervals will be <2,3> and <9,9>, i.e. the array will be
 * [1,1,0,0,1,1,1,1,1,0]
 * 
 * Then after calling getAndEraseNextFullSubinterval(4) it returns interval
 * <0,1>, and the array will be [0,0,0,0,1,1,1,1,1,0], And if you call
 * getAndEraseNextFullSubinterval(4) again, it returns interval <4,7>, and the
 * array will be [0,0,0,0,0,0,0,0,1,0].
 * 
 * Motivation to this structure is to model succesfully downloaded random parts
 * of the file.
 * 
 * The implementation does not use any array, but a sorted set of empty
 * subintervals due to performance.
 * 
 * The implementation requires Java 9 or later.
 * 
 * @author Peter Gursky
 *
 */
public class Interval implements Comparable<Interval> {

	private final long min;
	private final long max;
	private boolean empty;
	private long next; // used in getAndEraseNextFullSubinterval()
	private Semaphore notEmptySemaphore = new Semaphore(0);
	private TreeSet<Interval> emptySubintervals;

	public static Interval empty(long min, long max) throws IllegalArgumentException {
		return new Interval(min, max, true);
	}

	public static Interval full(long min, long max) throws IllegalArgumentException {
		return new Interval(min, max, false);
	}

	private Interval(long min, long max, boolean empty) throws IllegalArgumentException {
		if (min > max)
			throw new IllegalArgumentException("min must be smaller or equal to max");
		this.min = min;
		this.max = max;
		this.empty = empty;
		this.next = min;
		if (!empty) {
			emptySubintervals = new TreeSet<>(); // full - no empty subintervals
			notEmptySemaphore.release();
		}
	}

	/**
	 * Returns all empty subintervals. If whole interval is empty, the List
	 * containing this interval is returned
	 * 
	 * @param maxCount maximal count of returned intervals. If maxCount equals 0,
	 *                 all empty subintervals are returned
	 * @throws IllegalArgumentException if maxCount is smaller than 0
	 * @return empty subintervals
	 */

	public synchronized List<Interval> getEmptySubintervals(int maxCount) throws IllegalArgumentException {
		if (maxCount < 0)
			throw new IllegalArgumentException("maxCount must be a non-negative number");
		if (empty)
			return List.of(this);
		Iterator<Interval> iterator = emptySubintervals.iterator();
		List<Interval> intervals = new ArrayList<>();
		int count = 0;
		while (iterator.hasNext() && (maxCount == 0 || maxCount > count++)) {
			intervals.add(iterator.next());
		}
		return intervals;
	}

	/**
	 * method fills intersecting gaps inside the partially filled or empty interval
	 * 
	 * @param fullSubinterval
	 */
	public synchronized void addFullSubinterval(Interval fullSubinterval) {
		if (empty) {
			if (hasIntersectionWith(fullSubinterval)) {
				empty = false;
				emptySubintervals = new TreeSet<>();
				emptySubintervals.addAll(minus(fullSubinterval));
				notEmptySemaphore.release();
			}
		} else {
			Interval onTheLeft = emptySubintervals.floor(fullSubinterval);
			Iterator<Interval> tailIterator;
			if (onTheLeft == null) {
				tailIterator = emptySubintervals.iterator();
			}
			else {
				if (onTheLeft.hasIntersectionWith(fullSubinterval)) {
					tailIterator = emptySubintervals.tailSet(onTheLeft).iterator();
				} else {
					tailIterator = emptySubintervals.tailSet(fullSubinterval).iterator();
				}
			}
			List<Interval> newEmptyIntervals = new ArrayList<>();
			while (tailIterator.hasNext()) {
				Interval emptyInterval = tailIterator.next();
				if (!emptyInterval.hasIntersectionWith(fullSubinterval))
					break;
				tailIterator.remove();
				newEmptyIntervals.addAll(emptyInterval.minus(fullSubinterval));
			}
			emptySubintervals.addAll(newEmptyIntervals);
		}
	}

	/**
	 * method fills intersecting gaps inside the partially filled or empty interval
	 * 
	 * @param min
	 * @param max
	 */
	public void addFullSubinterval(long min, long max) {
		addFullSubinterval(Interval.full(min, max));
	}

	/**
	 * Returns some full subinterval if there is any, otherwise returns null.
	 * Returned subinterval is changed to be empty.
	 * 
	 * @param maxIntervalLength upper bound of interval length
	 * @return
	 */
	public synchronized Interval getAndEraseNextFullSubinterval(long maxIntervalLength) {
		if (empty)
			return null;
		Interval smallerEmpty = emptySubintervals.floor(Interval.empty(next, next));
		long start = (smallerEmpty == null) ? next : Math.max(smallerEmpty.max + 1, next);
		if (start > max) {
			next = min;
			smallerEmpty = emptySubintervals.floor(Interval.empty(next, next));
			start = (smallerEmpty == null) ? next : Math.max(smallerEmpty.max + 1, next);
		}
		Interval greaterEmpty = emptySubintervals.higher(Interval.empty(start, start));
		long end = (greaterEmpty == null) ? Math.min(start + maxIntervalLength - 1, max)
				: Math.min(start + maxIntervalLength - 1, greaterEmpty.min - 1);
		Interval result = Interval.empty(start, end);
		next = (end == max) ? min : end + 1;
		if (smallerEmpty != null && smallerEmpty.max == result.min - 1) { // inrease empty space on the left
			emptySubintervals.remove(smallerEmpty);
			smallerEmpty = Interval.empty(smallerEmpty.min, result.max);
			emptySubintervals.add(smallerEmpty);
		} else {
			if (greaterEmpty != null && greaterEmpty.min - 1 == result.max) { // increase empty space on the right
				emptySubintervals.remove(greaterEmpty);
				greaterEmpty = Interval.empty(start, greaterEmpty.max);
				emptySubintervals.add(greaterEmpty);
			} else {
				emptySubintervals.add(result); // insert empty space inside filled space
			}
		}
		if (smallerEmpty != null && greaterEmpty != null && smallerEmpty.max == greaterEmpty.min - 1) { // join neighbor
																										// empty spaces
			emptySubintervals.remove(smallerEmpty);
			emptySubintervals.remove(greaterEmpty);
			emptySubintervals.add(Interval.empty(smallerEmpty.min, greaterEmpty.max));
		}
		notEmptySemaphore.drainPermits();

		if (emptySubintervals.size() == 1) {
			Interval interval = emptySubintervals.first();
			if (interval.min == min && interval.max == max) { // if whole interval is filled with empty space, make it
																// empty
				emptySubintervals = null;
				empty = true;
			} else {
				notEmptySemaphore.release();
			}
		} else {
			notEmptySemaphore.release();
		}
		return result;
	}

	/**
	 * Returns some full subinterval if there is any. If not, the method is blocked
	 * until any full subinterval is available.
	 * 
	 * @param maxIntervalLength upper bound of interval length
	 * @return
	 * @throws InterruptedException
	 */
	public Interval getAndEraseNextFullSubintervalBlocked(long maxIntervalLength)
			throws InterruptedException {
		notEmptySemaphore.acquire();
		Interval interval = getAndEraseNextFullSubinterval(maxIntervalLength);
		return interval;
	}

	public synchronized boolean isEmpty() {
		return empty;
	}

	public synchronized boolean isFull() {
		if (empty)
			return false;
		return emptySubintervals.size() == 0;
	}

	public long length() {
		return max - min + 1;
	}

	@Override
	public int compareTo(Interval o) {
		return Long.compare(min, o.min);
	}

	/**
	 * Checks if the value is inside some empty subinterval
	 * 
	 * @param value
	 * @return true if the value is inside some empty subinterval
	 */
	public synchronized boolean isMissing(long value) {
		if (empty) {
			return min <= value && max >= value;
		} else {
			for (Interval interval: emptySubintervals) {
				if (interval.min <= value && interval.max >= value) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasIntersectionWith(Interval other) {
		return ((other.min < max && other.max > min) || (min < other.max && max > other.min));
	}
	
	private List<Interval> minus(Interval fullInterval) {
		List<Interval> result = new ArrayList<>();
		if (min < fullInterval.min) {
			result.add(Interval.empty(min, Math.min(max, fullInterval.min - 1)));
		}
		if (max > fullInterval.max) {
			result.add(Interval.empty(Math.max(min, fullInterval.max + 1), max));
		}
		return result;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "Interval [" + min + ", " + max + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (max ^ (max >>> 32));
		result = prime * result + (int) (min ^ (min >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		if (max != other.max)
			return false;
		if (min != other.min)
			return false;
		return true;
	}
}
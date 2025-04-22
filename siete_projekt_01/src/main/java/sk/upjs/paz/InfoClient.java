package sk.upjs.paz;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

public class InfoClient {

    public static void main(String[] args) {

        DatagramSocket receiveDataSocket = null;

        try (DatagramSocket getInfoSocket = new DatagramSocket()) {

            byte[] bytesToSend = "sendFileMetadata".getBytes();
            DatagramPacket packetToSend = new DatagramPacket(bytesToSend, bytesToSend.length,
                    InetAddress.getByName("localhost"), Consts.INFO_SERVER_PORT);
            getInfoSocket.send(packetToSend);

            byte[] receivedInfoData = new byte[getInfoSocket.getReceiveBufferSize()];
            DatagramPacket requestPacket = new DatagramPacket(receivedInfoData, receivedInfoData.length);
            getInfoSocket.receive(requestPacket);

            String requestedInfoMessage = new String(requestPacket.getData()).trim();

            String[] mesageParts = requestedInfoMessage.split(";");
            Long fileSize = Long.valueOf(mesageParts[1]);
            System.out.println("File on server: " + mesageParts[0] + ", with size: " + fileSize);

            // idem prijimat subor subor a pripravim si veci nan

            // prazdny interval, ktory budem naplnat ako mi budu chodit data
            Interval intervalsIHave = Interval.empty(0, fileSize);

            // subor kam budem ukladat a raf lebo vie chodit po subore kade tade a tam
            // zapisovat/citat
            File fileToSave = new File(mesageParts[0]);
            RandomAccessFile raf = new RandomAccessFile(fileToSave, "rw");
            raf.setLength(fileSize);

            // otvorim socket na porte kde budem ocakavat data a nastavim timeout ak istu
            // dobu nepridu data, tak budem ziadat intervaly, ktore nemam
            receiveDataSocket = new DatagramSocket(Consts.DATA_CLIENT_PORT);
            receiveDataSocket.setSoTimeout(Consts.TIMEOUT + (int) (Math.random() * 50));
            byte[] receivedFileChunkDataByte;

            // finalne nastavim na kym nemam vsetky data
            while (true) {

                if(intervalsIHave.isFull()){
                    raf.close();
                    receiveDataSocket.close();
                    System.out.println("hotovo");
                    break;
                }
                try {
                    receivedFileChunkDataByte = new byte[getInfoSocket.getReceiveBufferSize()];
                    DatagramPacket receivedFileChunkPacket = new DatagramPacket(receivedFileChunkDataByte,
                            receivedFileChunkDataByte.length);
                    receiveDataSocket.receive(receivedFileChunkPacket);

                    // TODO dokoncit doma!
                    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(receivedFileChunkDataByte))) {
                        long min = ois.readLong();
                        long max = ois.readLong();
                        int length = (int) (max - min + 1);
                        byte[] data = new byte[length];
                        ois.readFully(data);

                        raf.seek(min);
                        raf.write(data);

                        intervalsIHave.addFullSubinterval(min, max);
                    }
                    // naplnanie suboru nejakym sposobom, pomocou toho co mi pride a metod seek a write v raf

                    // ista doba presla, idem ziadat intervaly, ktore nemam
                } catch (SocketTimeoutException e) {

                    // ziskam intervaly, ktore nemam
                    List<Interval> emptySubintervals = intervalsIHave.getEmptySubintervals(Consts.INTERVALS_IN_REQUEST);
                    // vytvaram veci, pomocou ktorych budem vediet vytvorit byte pole (hore som
                    // robil zo stringu, teraz neviem) a teda baos a oos, kde zratam velkost
                    // buduceho byte pola, ako 4(int) + 16(2xlong) * pocet chybajucich intervalov
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(4 + 16 * emptySubintervals.size());
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    // no a postupne do toho "pola" zapisem potrebne veci
                    oos.writeInt(emptySubintervals.size());
                    for (Interval interval : emptySubintervals) {
                        oos.writeLong(interval.getMin());
                        oos.writeLong(interval.getMax());
                    }
                    oos.flush();
                    // a poslem na sever na port kde caka ziadosti na posielanie dat
                    DatagramPacket requestData = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length,
                            InetAddress.getByName("localhost"), Consts.REQUEST_SERVER_PORT);
                    getInfoSocket.send(requestData);
                }

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (receiveDataSocket != null) {
                receiveDataSocket.close();
            }
        }

    }

}

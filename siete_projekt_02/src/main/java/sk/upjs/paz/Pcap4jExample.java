package sk.upjs.paz;

import java.net.InetAddress;
import java.util.*;

import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.EthernetPacket;

import org.pcap4j.packet.Packet;

public class Pcap4jExample {

	public static void main(String[] args) throws Exception {

		Map<String, String> SrcAddr = new HashMap<>();
		Map<String, String> DstAddr = new HashMap<>();
		Map<String, Long> SrcIPTimeOut = new HashMap<>();
		Map<String, Long> DstIPTimeOut = new HashMap<>();

		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr);
		PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);
		int snapLen = 65536;
		PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
		int timeout = 10;
		PcapHandle handle = nif.openLive(snapLen, mode, timeout);
		handle.loop(-1, new PacketListener() {
			@Override
			public void gotPacket(Packet packet) {
				ArpPacket arpPacket = packet.get(ArpPacket.class);

				if (arpPacket != null) {
					String sourceIP = arpPacket.getHeader().getSrcProtocolAddr().getHostAddress();
					String destinationIP = arpPacket.getHeader().getDstProtocolAddr().getHostAddress();
					String sourceMacAddress = arpPacket.getHeader().getSrcHardwareAddr().toString();
					String destinationMacAddress = arpPacket.getHeader().getDstHardwareAddr().toString();

					EthernetPacket ethPacket = packet.get(EthernetPacket.class);
					String srcMacAdress = ethPacket.getHeader().getSrcAddr().toString();
					String destMacAdress = ethPacket.getHeader().getDstAddr().toString();

					String operation = arpPacket.getHeader().getOperation().toString();

					System.out.print("Sender IP: " + sourceIP);
					System.out.print(" Source MAC Address: " + sourceMacAddress);
					System.out.println(" ina src MAC Address: " + srcMacAdress);

					System.out.print(" Destination IP: " + destinationIP);
					System.out.print(" Destination MAC Address: " + destinationMacAddress);
					System.out.println(" ina dest MAC Address: " + destMacAdress);
					System.out.println(" Operation: " + operation);
					System.out.println("----------------------------------------------------");


					// zapis source IP adries s prisluchajucimi MAC adresami do mapy
					if (!SrcAddr.containsKey(sourceIP)) {
						SrcAddr.put(sourceIP, sourceMacAddress);

						// zapis source IP adries s prisluchajucim casom ich zapisu
						long startSrc = System.nanoTime();
						SrcIPTimeOut.put(sourceIP, startSrc);
					}

					// zapis destination IP adries s prichluchajucimi MAC adresami do mapy
					if (!DstAddr.containsKey(destinationIP)) {
						DstAddr.put(destinationIP, destinationMacAddress);

						// zapis destination IP adries s prisluchajucim casom ich zapisu
						long startDst = System.nanoTime();
						DstIPTimeOut.put(destinationIP, startDst);
					}

					// kontrolujem ci MAC adresa nie je rozdielna od prisluchajucej IP adresy
					// a ci ku tomuto rozdielu doslo do 60 sekund od prijatia arp packetu s danou IP adresou
					if ((!SrcAddr.get(sourceIP).equals(sourceMacAddress) &&
							(System.nanoTime() - SrcIPTimeOut.get(sourceIP)) <= 60_000_000_000L)
							|| (!DstAddr.get(destinationIP).equals(destinationMacAddress) &&
							(System.nanoTime() - DstIPTimeOut.get(destinationIP) <= 60_000_000_000L))) {
						throw new RuntimeException("daco barz nedobre");
					}
				}
			}
		});
	}
}


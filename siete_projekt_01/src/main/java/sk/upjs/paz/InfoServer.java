package sk.upjs.paz;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoServer {

	public static void main(String[] args) {

		try (DatagramSocket infoSocket = new DatagramSocket(Consts.INFO_SERVER_PORT)) {

			System.out.println("Listening on port " + Consts.INFO_SERVER_PORT + "!");

			// pripravim si data na poslanie
			File file = new File(Consts.FILE_TO_COPY);
			String fileNameToSend = file.getName();
			long sizeOfFileToSend = file.length();
			String infoMessage = fileNameToSend + ";" + sizeOfFileToSend;

			ExecutorService es = Executors.newCachedThreadPool();
			Interval partsToSend = Interval.empty(0, sizeOfFileToSend);
			es.execute(new BroadcastTask(partsToSend, file));
			es.execute(new RequestServerTask(partsToSend));

			// server stale pocuva na klientov a ked tak im posle info o subore
			while (true) {
				byte[] receivedData = new byte[infoSocket.getReceiveBufferSize()];
				DatagramPacket requestPacket = new DatagramPacket(receivedData, receivedData.length);
				infoSocket.receive(requestPacket);

				String requestMessage = new String(requestPacket.getData()).trim();
				if ("sendFileMetadata".equals(requestMessage)) {
					System.out.println("Sending file info to " + requestPacket.getAddress());
					byte[] infoMessageBytes = infoMessage.getBytes();
					DatagramPacket packetToSend = new DatagramPacket(infoMessageBytes, infoMessageBytes.length,
							requestPacket.getAddress(), requestPacket.getPort());
					infoSocket.send(packetToSend);
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

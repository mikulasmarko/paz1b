package sk.upjs.paz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RequestServerTask implements Runnable {

    private Interval partsToSend;

    public RequestServerTask(Interval partsToSend) {
        this.partsToSend = partsToSend;
    }

    @Override
    public void run() {


        try (DatagramSocket requestSocket = new DatagramSocket(Consts.REQUEST_SERVER_PORT)) {
            byte[] buffer = new byte[requestSocket.getReceiveBufferSize()];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                requestSocket.receive(packet);

                // ziskanie poctu intervalov
                int count;
                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
                    count = ois.readInt();
                    for (int i = 0; i < count; i++) {
                        long min = ois.readLong();
                        long max = ois.readLong();
                        partsToSend.addFullSubinterval(min, max);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO dokoncit doma!
        // primanie ziadosti o chunky a ich pridavanie do partsToSend

    }

}

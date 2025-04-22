package sk.upjs.paz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastTask implements Runnable {

    private Interval partsToSend;
    private File fileToSend;
    private RandomAccessFile raf;

    public BroadcastTask(Interval partsToSend, File fileToSend) {
        this.partsToSend = partsToSend;
        this.fileToSend = fileToSend;

        try {
            raf = new RandomAccessFile(fileToSend, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Interval intervalToSend = partsToSend.getAndEraseNextFullSubintervalBlocked(Consts.DATA_CHUNK_SIZE);

                raf.seek(intervalToSend.getMin());
                byte[] chunkFromFile = new byte[Consts.DATA_CHUNK_SIZE];
                // ulozim si, lebo mozno pouzijem pri poslednom chunku a mozno nie
                int reallyRead = raf.read(chunkFromFile);

                // tieto data ulozim do noveho pola, kde bude aj odkial pokial to ide v
                // intervale, aby som vedel

                // pozor na Consts.DATA_CHUNK_SIZE v riadku nizsie, na cviku sme nejako doratavali, pretoze pri poslednom chunku to bude mensie cislo, ako to zriesite, nechavam na vas, moznosti je viacero
                ByteArrayOutputStream baos = new ByteArrayOutputStream(8 + 4 + Consts.DATA_CHUNK_SIZE);
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeLong(intervalToSend.getMin());
                oos.writeLong(intervalToSend.getMax());
                oos.write(chunkFromFile);
                oos.flush();

                DatagramPacket requestData = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length,
                        InetAddress.getByName("255.255.255.255"), Consts.DATA_CLIENT_PORT);

                try (DatagramSocket socket = new DatagramSocket()) {
                    socket.setBroadcast(true);
                    socket.send(requestData);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}

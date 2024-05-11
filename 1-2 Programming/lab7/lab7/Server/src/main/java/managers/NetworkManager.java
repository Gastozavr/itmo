package managers;

import commands.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Record;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NetworkManager {
    DatagramChannel dc;
    int port;
    SocketAddress addr;
    int timeout;
    byte[] bytes = new byte[5096];


    public NetworkManager(int port, int timeout) {
        this.port = port;
    }

    public static final Logger logger = LoggerFactory.getLogger(NetworkManager.class);

    public boolean init() {
        try {
            addr = new InetSocketAddress(port);
            dc = DatagramChannel.open();
            dc.bind(addr);
            dc.configureBlocking(false);
            return true;
        } catch (SocketException e) {
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendData(Record record) {
        try {
            ByteBuffer buf = ByteBuffer.wrap(record.getArr());
            dc.send(buf, record.getAddr());
            return true;
        } catch (IOException e) {
            return false;

        }
    }

    public boolean checkArray(byte[] array) {
        for (var e : array) {
            if (e != 0) return true;
        }
        return false;
    }

    public Record receiveData(int len) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(len);
            addr = dc.receive(buf);
            if (addr != null) {
                logger.info("Получен запрос от клиента!");
                if (checkArray(buf.array())) {
                    return new Record(buf.array(), addr);
                }
                else return null;
            }
            return null;
        } catch (IOException e) {
            logger.error("Не удалось получить данные.", e);
            return null;
        }
    }

    public static byte[] serializer(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
            byte[] objBytes = bos.toByteArray();
            logger.info("Ответ успешно сериализован!");
            return objBytes;

        } catch (IOException e) {
            return null;
        }
    }

    public static Container deserialize(byte[] bytes) {
        if (bytes == null) return null;
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            logger.info("Команда успешно десериализована!");
            return (Container) ois.readObject();
        } catch (IOException e) {
            logger.error("Не удалось десереализовать объект");
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Не удалось десереализовать объект");
            return null;
        }
    }

}

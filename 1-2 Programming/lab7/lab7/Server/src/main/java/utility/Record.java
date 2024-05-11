package utility;

import java.net.SocketAddress;

public class Record {
    private byte[] arr;
    private SocketAddress addr;

    public Record(byte[] arr, SocketAddress addr) {
        this.arr = arr;
        this.addr = addr;
    }
    public byte[] getArr() {
        return arr;
    }
    public SocketAddress getAddr() {
        return addr;
    }

}

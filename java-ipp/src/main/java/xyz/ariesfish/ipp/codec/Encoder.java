package xyz.ariesfish.ipp.codec;

import java.io.OutputStream;

public class Encoder {

    private OutputStream output;

    public Encoder(OutputStream out) {
        output = out;
    }

    public boolean encode(Packet packet) {
        return false;
    }
}

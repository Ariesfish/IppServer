package xyz.ariesfish.ipp.codec;

import java.io.InputStream;

public class Decoder {
    private InputStream input;

    public Decoder(InputStream in) {
        input = in;
    }

    public boolean decode(Packet packet) {
        return false;
    }
}

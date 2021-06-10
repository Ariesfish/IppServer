package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

import java.io.Serializable;

public interface Value extends Serializable {
    Type getType();

    boolean decode(byte[] data);

    byte[] encode();
}

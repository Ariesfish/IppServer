package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class RangeValue implements Value {
    private final static int SIZE = 8;

    private int lowerBound;
    private int upperBound;

    public RangeValue() {
        this(0, 0);
    }

    public RangeValue(int l, int u) {
        lowerBound = l;
        upperBound = u;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", lowerBound, upperBound);
    }

    @Override
    public Type getType() {
        return Type.RANGE;
    }

    @Override
    public boolean decode(byte[] data) {
        if (data == null || data.length != SIZE) {
            return false;
        } else {
            lowerBound = ((data[0] & 0xff) << 24)
                    | ((data[1] & 0xff) << 16)
                    | ((data[2] & 0xff) << 8)
                    | (data[3] & 0xff);
            upperBound = ((data[4] & 0xff) << 24)
                    | ((data[5] & 0xff) << 16)
                    | ((data[6] & 0xff) << 8)
                    | (data[7] & 0xff);
            return true;
        }
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[SIZE];
        data[0] = (byte)((lowerBound & 0xff000000) >> 24);
        data[1] = (byte)((lowerBound & 0xff0000) >> 16);
        data[2] = (byte)((lowerBound & 0xff00) >> 8);
        data[3] = (byte)(lowerBound & 0xff);
        data[4] = (byte)((upperBound & 0xff000000) >> 24);
        data[5] = (byte)((upperBound & 0xff0000) >> 16);
        data[6] = (byte)((upperBound & 0xff00) >> 8);
        data[7] = (byte)(upperBound & 0xff);

        return data;
    }
}

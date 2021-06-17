package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

public class ResolutionValue implements Value {
    private final static int SIZE = 9;

    public final static int DPI = 3;
    public final static int DPC = 4;

    private int resX;
    private int resY;
    private int unit;

    public ResolutionValue() {
        this(0, 0, 3);
    }

    public ResolutionValue(int x, int y, int u) {
        resX = x;
        resY = y;
        unit = u;
    }

    @Override
    public String toString() {
        if (unit == DPC) {
            return String.format("%dx%d%s", resX, resY, "dpc");
        } else {
            return String.format("%dx%d%s", resX, resY, "dpi");
        }
    }

    @Override
    public Type getType() {
        return Type.RESOLUTION;
    }

    @Override
    public boolean decode(byte[] data) {
        if (data == null || data.length != SIZE) {
            return false;
        } else {
            resX = ((data[0] & 0xff) << 24)
                    | ((data[1] & 0xff) << 16)
                    | ((data[2] & 0xff) << 8)
                    | (data[3] & 0xff);
            resY = ((data[4] & 0xff) << 24)
                    | ((data[5] & 0xff) << 16)
                    | ((data[6] & 0xff) << 8)
                    | (data[7] & 0xff);
            unit = data[8] & 0xff;
            return true;
        }
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}

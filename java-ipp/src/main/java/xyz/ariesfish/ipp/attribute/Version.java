package xyz.ariesfish.ipp.attribute;

import java.util.Objects;

public class Version {
    public final static int VERSION_1_1 = 0x0101;
    public final static int VERSION_2_0 = 0x0200;
    public final static int VERSION_2_1 = 0x0201;

    private int value;

    public Version() {
        value = VERSION_2_0;
    }

    public Version(int v) {
        value = v;
    }

    public int major() {
        return (value & 0xff00) >> 8;
    }

    public int minor() {
        return value & 0xff;
    }

    @Override
    public String toString() {
        return String.format("%d.%d", major(), minor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return value == version.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int compare(Version v) {
        if (major() > v.major()) {
            return 1;
        } else if (major() < v.major()) {
            return -1;
        } else {
            if (minor() > v.major()) {
                return 1;
            } else if (minor() < v.major()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

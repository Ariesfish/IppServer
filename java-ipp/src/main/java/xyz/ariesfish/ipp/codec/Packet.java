package xyz.ariesfish.ipp.codec;

import lombok.Getter;
import lombok.Setter;
import xyz.ariesfish.ipp.attribute.Attribute;
import xyz.ariesfish.ipp.attribute.Attributes;
import xyz.ariesfish.ipp.attribute.Tag;
import xyz.ariesfish.ipp.attribute.Version;

import java.io.InputStream;
import java.io.OutputStream;

public class Packet {

    @Getter
    @Setter
    private Version version;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private int requestId;

    @Getter
    @Setter
    private boolean isRequest;

    @Getter
    private final Attributes operations;

    @Getter
    private final Attributes jobs;

    @Getter
    private final Attributes printers;

    @Getter
    private final Attributes unsupported;

    public Packet() {
        this(true);
    }

    public Packet(boolean isRequest) {
        this(new Version(), 0, 0, isRequest);
    }

    public Packet(Version v, int c, int id, boolean isRequest) {
        version = v;
        code = c;
        requestId = id;
        this.isRequest = isRequest;
        operations = new Attributes(new Tag(Tag.OPERATION_ATTRIBUTES));
        jobs = new Attributes(new Tag(Tag.JOB_ATTRIBUTES));
        printers = new Attributes(new Tag(Tag.PRINTER_ATTRIBUTES));
        unsupported = new Attributes(new Tag(Tag.UNSUPPORTED_ATTRIBUTES));
    }

    public Packet addOperationAttribute(Attribute operation) {
        operations.add(operation);
        return this;
    }

    public Packet addJobAttribute(Attribute job) {
        jobs.add(job);
        return this;
    }

    public Packet addPrinterAttribute(Attribute printer) {
        printers.add(printer);
        return this;
    }

    public Packet addUnsupportedAttribute(Attribute unsupported) {
        this.unsupported.add(unsupported);
        return this;
    }

    public boolean decode(InputStream in) {
        Decoder decoder = new Decoder(in);
        return decoder.decode(this);
    }

    public boolean encode(OutputStream out) {
        Encoder encoder = new Encoder(out);
        return encoder.encode(this);
    }

    @Override
    public String toString() {
        return "Packet{" +
                "version=" + version +
                ", code=" + code +
                ", requestId=" + requestId +
                ", isRequest=" + isRequest +
                ", operations=" + operations +
                ", jobs=" + jobs +
                ", printers=" + printers +
                ", unsupported=" + unsupported +
                '}';
    }
}

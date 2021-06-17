package xyz.ariesfish.ipp.codec;

import lombok.Getter;
import lombok.Setter;
import xyz.ariesfish.ipp.attribute.*;

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
        StringBuilder builder = new StringBuilder();
        if (isRequest) {
            builder.append("IPP Request");
        } else {
            builder.append("IPP Response");
        }
        builder.append("\n");

        builder.append("Version: ").append(version).append("\n");
        builder.append("Code: ");
        if (isRequest) {
            builder.append(Operation.description(code));
        } else {
            builder.append(Status.description(code));
        }
        builder.append("\n");

        builder.append("Request ID: ").append(requestId).append("\n");

        if (!operations.isEmpty()) {
            builder.append("Operation Attributes:").append("\n");
            builder.append(operations);
        }
        if (!jobs.isEmpty()) {
            builder.append("Job Attributes:").append("\n");
            builder.append(jobs);
        }
        if (!printers.isEmpty()) {
            builder.append("Printer Attributes:").append("\n");
            builder.append(printers);
        }
        if (!unsupported.isEmpty()) {
            builder.append("Unsupported Attributes:").append("\n");
            builder.append(unsupported);
        }

        return builder.toString();
    }
}

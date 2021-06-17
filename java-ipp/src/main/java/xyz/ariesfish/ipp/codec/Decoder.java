package xyz.ariesfish.ipp.codec;

import xyz.ariesfish.ipp.attribute.Attribute;
import xyz.ariesfish.ipp.attribute.Attributes;
import xyz.ariesfish.ipp.attribute.Tag;
import xyz.ariesfish.ipp.attribute.Version;
import xyz.ariesfish.ipp.value.CollectionValue;
import xyz.ariesfish.ipp.value.ValueMember;

import java.io.IOException;
import java.io.InputStream;

public class Decoder {
    private InputStream input;

    public Decoder(InputStream in) {
        input = in;
    }

    public boolean decode(Packet packet) {
        Version v = decodeVersion();
        if (v == null) {
            return false;
        }
        packet.setVersion(v);

        int code = decodeCode(packet.isRequest());
        if (code == -1) {
            return false;
        }
        packet.setCode(code);

        int requestId = decodeRequestId();
        if (requestId == 0) {
            return false;
        }
        packet.setRequestId(requestId);

        boolean finished = false;
        Attributes group = null;
        Attribute prevAttr = null;
        Attribute attr = null;

        while (!finished) {
            Tag tag = decodeTag();
            int tagValue = tag.getTag();

            if (tag.isDelimiter()) {
                prevAttr = null;

                if (Tag.END_OF_ATTRIBUTES == tagValue) {
                    finished = true;
                } else if (Tag.OPERATION_ATTRIBUTES == tagValue) {
                    group = packet.getOperations();
                } else if (Tag.JOB_ATTRIBUTES == tagValue) {
                    group = packet.getJobs();
                } else if (Tag.PRINTER_ATTRIBUTES == tagValue) {
                    group = packet.getPrinters();
                } else if (Tag.UNSUPPORTED_ATTRIBUTES == tagValue) {
                    group = packet.getUnsupported();
                }
            } else {
                if (Tag.MEMBER_NAME == tagValue || Tag.END_COLLECTION == tagValue) {
                    // TODO log error
                } else {
                    attr = decodeAttribute(tag);
                }

                if (attr != null) {
                    if (Tag.BEGIN_COLLECTION == tagValue) {
                        CollectionValue col = decodeCollection();
                        if (col != null) {
                            attr.setValue(0, col);
                        }
                    }

                    if (attr.getName().isEmpty()) {
                        if (prevAttr != null) {
                            prevAttr.addValue(attr.getValueMember(0));
                        } else {
                            // TODO log error
                        }
                    } else {
                        if (group != null) {
                            group.add(attr);
                            prevAttr = group.get(group.size()-1);
                        } else {
                            // TODO log error
                        }
                    }
                } else {
                    // TODO log error
                }
            }
        }

        return true;
    }

    private Version decodeVersion() {
        Version version = null;
        try {
            int v = decodeU16();
            version = new Version(v);
        } catch (IOException e) {
            // TODO log error
        }
        return version;
    }

    private int decodeCode(boolean isRequest) {
        int code = -1;

        try {
            code = decodeU16();
            if (isRequest) {
                // TODO
            } else {
                // TODO
            }
        } catch (IOException e) {
            // TODO
        }
        return code;
    }

    private int decodeRequestId() {
        int requestId = 0;

        try {
            requestId = decodeU32();
            // TODO
        } catch (IOException e) {
            // TODO
        }
        return requestId;
    }

    private Tag decodeTag() {
        Tag tag = null;

        try {
            int t = decodeU8();
            tag = new Tag(t);

            if (tag.isDelimiter()) {
                // TODO
            }
        } catch (IOException e) {
            // TODO
        }
        return tag;
    }

    private Attribute decodeAttribute(Tag tag) {
        Attribute attr;

        try {
            attr = new Attribute();
            attr.setName(decodeName());

            byte[] value = decodeBytes();
            attr.unpackValue(tag, value);
        } catch (IOException e) {
            attr = null;
            // TODO
        }

        return attr;
    }

    private CollectionValue decodeCollection() {
        CollectionValue collection = new CollectionValue();

        while (true) {
            Tag tag = decodeTag();
            if (tag.isDelimiter()) {
                // TODO log error
                return null;
            }

            if (Tag.MEMBER_NAME == tag.getTag() || Tag.END_COLLECTION == tag.getTag()) {
                int len = collection.size();
                if (len > 0 && collection.get(len-1).valueSize() == 0) {
                    // TODO log error
                    return null;
                }
            }

            Attribute attr = decodeAttribute(tag);
            if (attr == null) {
                // TODO log error
                return null;
            }

            if (Tag.END_COLLECTION == tag.getTag()) {
                return collection;
            } else if (Tag.MEMBER_NAME == tag.getTag()) {
                attr.setName(attr.getValueMember(0).getValue().toString());
                if (attr.getName().isEmpty()) {
                    // TODO log error
                    return null;
                }

                collection.add(attr);
            } else if (collection.size() == 0) {
                // TODO log error
                return null;
            } else {
                if (Tag.BEGIN_COLLECTION == tag.getTag()) {
                    CollectionValue col = decodeCollection();
                    if (col == null) {
                        return null;
                    } else {
                        attr.setValue(0, col);
                    }
                }

                int len = collection.size();
                collection.get(len-1).addValue(tag, attr.getValueMember(0).getValue());
            }
        }
    }

    private int decodeU8() throws IOException {
        int a = input.read();
        return (a & 0xff);
    }

    private int decodeU16() throws IOException {
        int a = input.read();
        int b = input.read();
        return ((a & 0xff) << 8) | (b & 0xff);
    }

    private int decodeU32() throws IOException {
        int a = input.read();
        int b = input.read();
        int c = input.read();
        int d = input.read();
        return ((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff);
    }

    private byte[] decodeBytes() throws IOException {
        int length = decodeU16();
        byte[] data = new byte[length];
        input.read(data);
        return data;
    }

    private String decodeName() throws IOException {
        byte[] data = decodeBytes();
        if (data.length > 0) {
            return new String(data);
        } else {
            return "";
        }
    }
}

package xyz.ariesfish.ipp.attribute;

import lombok.Getter;

public class Tag {

    /**
     * delimiter tags
     */
    public final static int BEGIN_ATTRIBUTE_GROUP = 0x00;
    public final static int OPERATION_ATTRIBUTES = 0x01;
    public final static int JOB_ATTRIBUTES = 0x02;
    public final static int END_OF_ATTRIBUTES = 0x03;
    public final static int PRINTER_ATTRIBUTES = 0x04;
    public final static int UNSUPPORTED_ATTRIBUTES = 0x05;

    /**
     * value tags
     */
    public final static int UNSUPPORTED_VALUE = 0x10;
    public final static int DEFAULT_VALUE = 0x11;
    public final static int UNKNOWN_VALUE = 0x12;
    public final static int NO_VALUE = 0x13;

    public final static int INTEGER_VALUE = 0x21;
    public final static int BOOLEAN_VALUE = 0x22;
    public final static int ENUM_VALUE = 0x23;

    public final static int STRING_VALUE = 0x30;
    public final static int DATETIME_VALUE = 0x31;
    public final static int RESOLUTION_VALUE = 0x32;
    public final static int RANGE_VALUE = 0x33;
    public final static int BEGIN_COLLECTION = 0x34;
    public final static int TEXT_LANG_VALUE = 0x35;
    public final static int NAME_LANG_VALUE = 0x36;
    public final static int END_COLLECTION = 0x37;

    public final static int TEXT_VALUE = 0x41;
    public final static int NAME_VALUE = 0x42;
    public final static int KEYWORD_VALUE = 0x44;
    public final static int URI_VALUE = 0x45;
    public final static int URI_SCHEME_VALUE = 0x46;
    public final static int CHARSET_VALUE = 0x47;
    public final static int LANGUAGE_VALUE = 0x48;
    public final static int MIME_VALUE = 0x49;
    public final static int MEMBER_NAME = 0x4A;

    @Getter
    private int tag;

    public Tag(int t) {
        tag = t;
    }

    public boolean isDelimiter() {
        return tag < 0x10;
    }

    public Type getType() {
        if (isDelimiter()) {
            return Type.INVALID;
        }

        Type type;
        switch (tag) {
            case UNSUPPORTED_VALUE:
            case DEFAULT_VALUE:
            case UNKNOWN_VALUE:
            case NO_VALUE:
            case END_COLLECTION:
                type = Type.NONE;
                break;
            case INTEGER_VALUE:
            case ENUM_VALUE:
                type = Type.INTEGER;
                break;
            case BOOLEAN_VALUE:
                type = Type.BOOLEAN;
                break;
            case STRING_VALUE:
            case TEXT_VALUE:
            case NAME_VALUE:
            case KEYWORD_VALUE:
            case URI_VALUE:
            case URI_SCHEME_VALUE:
            case CHARSET_VALUE:
            case LANGUAGE_VALUE:
            case MIME_VALUE:
            case MEMBER_NAME:
                type = Type.STRING;
                break;
            case DATETIME_VALUE:
                type = Type.DATETIME;
                break;
            case RESOLUTION_VALUE:
                type = Type.RESOLUTION;
                break;
            case RANGE_VALUE:
                type = Type.RANGE;
                break;
            case TEXT_LANG_VALUE:
            case NAME_LANG_VALUE:
                type = Type.TEXT_WITH_LANG;
                break;
            case BEGIN_COLLECTION:
                type = Type.COLLECTION;
                break;
            default:
                type = Type.BINARY;
                break;
        }

        return type;
    }

    @Override
    public String toString() {
        String desc;

        switch (tag) {
            case BEGIN_ATTRIBUTE_GROUP:
                desc = "begin-attribute-group";
                break;
            case OPERATION_ATTRIBUTES:
                desc = "operation-attributes-tag";
                break;
            case JOB_ATTRIBUTES:
                desc = "job-attributes-tag";
                break;
            case END_OF_ATTRIBUTES:
                desc = "end-of-attributes-tag";
                break;
            case PRINTER_ATTRIBUTES:
                desc = "printer-attributes-tag";
                break;
            case UNSUPPORTED_ATTRIBUTES:
                desc = "unsupported-attributes-tag";
                break;
            case UNSUPPORTED_VALUE:
                desc = "unsupported";
                break;
            case DEFAULT_VALUE:
                desc = "default";
                break;
            case UNKNOWN_VALUE:
                desc = "unknown";
                break;
            case NO_VALUE:
                desc = "no-value";
                break;
            case INTEGER_VALUE:
                desc = "integer";
                break;
            case BOOLEAN_VALUE:
                desc = "boolean";
                break;
            case ENUM_VALUE:
                desc = "enum";
                break;
            case STRING_VALUE:
                desc = "octetString";
                break;
            case DATETIME_VALUE:
                desc = "datetime";
                break;
            case RESOLUTION_VALUE:
                desc = "resolution";
                break;
            case RANGE_VALUE:
                desc = "rangeOfInteger";
                break;
            case BEGIN_COLLECTION:
                desc = "begCollection";
                break;
            case TEXT_LANG_VALUE:
                desc = "textWithLanguage";
                break;
            case NAME_LANG_VALUE:
                desc = "nameWithLanguage";
                break;
            case END_COLLECTION:
                desc = "endCollection";
                break;
            case TEXT_VALUE:
                desc = "textWithoutLanguage";
                break;
            case NAME_VALUE:
                desc = "nameWithoutLanguage";
                break;
            case KEYWORD_VALUE:
                desc = "keyword";
                break;
            case URI_VALUE:
                desc = "uri";
                break;
            case URI_SCHEME_VALUE:
                desc = "uriScheme";
                break;
            case CHARSET_VALUE:
                desc = "charset";
                break;
            case LANGUAGE_VALUE:
                desc = "naturalLanguage";
                break;
            case MIME_VALUE:
                desc = "mimeMediaType";
                break;
            case MEMBER_NAME:
                desc = "memberAttrName";
                break;
            default:
                desc = "unknown tag";
                break;
        }

        return String.format("%s (0x%02x)", desc, tag);
    }
}

package xyz.ariesfish.ipp.value;

import xyz.ariesfish.ipp.attribute.Type;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TextWithLangValue implements Value {
    private final static int SIZE = 2;

    private String language;
    private String text;

    public TextWithLangValue() {
        this("utf-8", "");
    }

    public TextWithLangValue(String l, String t) {
        language = l;
        text = t;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", text, language);
    }

    @Override
    public Type getType() {
        return Type.TEXT_WITH_LANG;
    }

    @Override
    public boolean decode(byte[] data) {
        int offset = 0;
        if (data.length < SIZE) {
            return false;
        }

        try {
            int langLen = ((data[offset] & 0xff) << 8) | (data[offset+1] & 0xff);
            offset += SIZE;
            if (data.length - offset < langLen) {
                return false;
            }
            language = new String(Arrays.copyOfRange(data, offset, offset+langLen));
            offset += langLen;

            int textLen = ((data[offset] & 0xff) << 8) | (data[offset+1] & 0xff);
            offset += SIZE;
            if (data.length - offset < textLen) {
                return false;
            }
            text = new String(Arrays.copyOfRange(data, offset, offset+langLen), language);

        } catch (UnsupportedEncodingException e) {
            // TODO: log error
            return false;
        }

        return true;
    }

    @Override
    public byte[] encode() {
        int langLen = language.length();
        int textLen = text.length();
        byte[] data = new byte[SIZE * 2 + langLen + textLen];
        int offset = 0;

        try {
            data[offset++] = (byte) ((langLen & 0xff00) >> 8);
            data[offset++] = (byte) (langLen & 0xff);
            byte[] langBytes = language.getBytes();
            System.arraycopy(langBytes, 0, data, offset, langLen);
            offset += langLen;

            data[offset++] = (byte) ((textLen & 0xff00) >> 8);
            data[offset++] = (byte) (textLen & 0xff);
            byte[] textBytes = text.getBytes(language);
            System.arraycopy(textBytes, 0, data, offset, textLen);

            return data;

        } catch(UnsupportedEncodingException e) {
            // TODO: log error
            return null;
        }
    }
}

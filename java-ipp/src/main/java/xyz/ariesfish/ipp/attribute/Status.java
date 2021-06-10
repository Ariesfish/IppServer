package xyz.ariesfish.ipp.attribute;

public final class Status {
    public final static int SUCCESSFUL_OK = 0x0000;

    public final static int CLIENT_ERROR_BAD_REQUEST = 0x4000;
    public final static int CLIENT_ERROR_NOT_POSSIBLE = 0x0404;

    public final static int SERVER_ERROR_OPERATION_NOT_SUPPORTED = 0x0501;
    public final static int SERVER_ERROR_VERSION_NOT_SUPPORTED = 0x0503;

    public static String description(int status) {
        String desc;

        switch (status) {
            case 0x0000:
                desc = "successful-ok";
                break;
            case 0x0400:
                desc = "client-error-bad-request";
                break;
            case 0x0404:
                desc = "client-error-not-possible";
                break;
            case 0x0501:
                desc = "server-error-operation-not-supported";
                break;
            case 0x0503:
                desc = "server-error-version-not-supported";
                break;
            default:
                desc = "unknown status";
                break;
        }

        return desc;
    }

    public static String toString(int status) {
        return String.format("%s (0x%04x)", description(status), status);
    }
}

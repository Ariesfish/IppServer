package xyz.ariesfish.ipp.attribute;

public final class Operation {
    public final static int PRINT_JOB               = 0x0002;
    public final static int PRINT_URI               = 0x0003;
    public final static int VALIDATE_JOB            = 0x0004;
    public final static int CREATE_JOB              = 0x0005;
    public final static int SEND_DOCUMENT           = 0x0006;
    public final static int SEND_URI                = 0x0007;
    public final static int CANCEL_JOB              = 0x0008;
    public final static int GET_JOB_ATTRIBUTES      = 0x0009;
    public final static int GET_JOBS                = 0x000A;
    public final static int GET_PRINTER_ATTRIBUTES  = 0x000B;
    public final static int HOLD_JOB                = 0x000C;

    public static String description(int operation) {
        String desc;

        switch (operation) {
            case 0x0002:
                desc = "Print-Job";
                break;
            case 0x0003:
                desc = "Print-URI";
                break;
            case 0x0004:
                desc = "Validate-Job";
                break;
            case 0x0005:
                desc = "Create-Job";
                break;
            case 0x0006:
                desc = "Send-Document";
                break;
            case 0x0007:
                desc = "Send-URI";
                break;
            case 0x0008:
                desc = "Cancel-Job";
                break;
            case 0x0009:
                desc = "Get-Job-Attributes";
                break;
            case 0x000A:
                desc = "Get-Jobs";
                break;
            case 0x000B:
                desc = "Get-Printer-Attributes";
                break;
            case 0x000C:
                desc = "Hold-Job";
                break;
            default:
                desc = "unknown operation";
        }

        return desc;
    }

    public static String toString(int operation) {
        return String.format("%s (0x%04x)", description(operation), operation);
    }
}

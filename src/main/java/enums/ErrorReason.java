package enums;

public enum ErrorReason{

    SUCCESS(1, "SUCCESS"),
    NO_CONFIG_FOUND(2, "NO_CONFIG_FOUND"),
    INVALID_PACKAGE_NAME(3, "INVALID_PACKAGE_NAME"),
    GENERAL_ERROR(4, "Unable to retrieve download link, ensure you have access to the internet");

    ErrorReason(int errorCode,String reason) {
        this.errorCode = errorCode;
        this.errorReason = reason;
    }

    private final String errorReason;
    private final int errorCode;

    public String getErrorReason() {
        return errorReason;
    }

    public int getErrorCode() {
        return errorCode;
    }

}

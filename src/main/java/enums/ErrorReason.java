package enums;

public enum ErrorReason{

    INVALID_VERSION_CODE("INVALID_VERSION_CODE"),
    INVALID_PACKAGE_NAME("INVALID_PACKAGE_NAME"),
    GENERAL("Ensure that you have racoon.jar installed and running on this system and that you are connected to the internet.");

    ErrorReason(String reason) {
        this.errorReason = reason;
    }

    public final String errorReason;
}

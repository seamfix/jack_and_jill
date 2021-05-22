package enums;

public enum ErrorReason{

    NO_CONFIG_FOUND("NO_CONFIG_FOUND"),
    INVALID_PACKAGE_NAME("INVALID_PACKAGE_NAME"),
    GENERAL("Ensure that you have racoon.jar installed and running on this system and that you are connected to the internet.");

    ErrorReason(String reason) {
        this.errorReason = reason;
    }

    public final String errorReason;
}

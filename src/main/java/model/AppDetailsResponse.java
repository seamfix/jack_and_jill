package model;

import enums.ErrorReason;

public class AppDetailsResponse {

    private int code;
    private String description;
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void assignResponse(ErrorReason errorReason) {
        this.setCode(errorReason.getErrorCode());
        this.setDescription(errorReason.getErrorReason());
    }

    @Override
    public String toString() {
        return "AppDetailsResponse{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}

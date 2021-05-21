package callback;

public interface OperationResponse {

    public void success(String downloadUrl);
    public void error(String errorMessage);
}

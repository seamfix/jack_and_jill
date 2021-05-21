import callback.OperationResponse;
import model.Application;

public class Main {

    /**
     * An example on how to use the library.
     */
    public static void main(String []args){

        Application application = new Application("com.looper.apps.loopercalculator");
        PlayStoreManager.getDownloadUrl(application, new OperationResponse() {
            public void success(String downloadUrl) {
                System.out.println("Download url: "+ downloadUrl);
            }

            public void error(String errorMessage) {
                System.out.println("Error message: "+errorMessage);
            }
        });
    }
}

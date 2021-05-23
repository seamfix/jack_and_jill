import callback.OperationResponse;
import model.Application;
import model.Config;

public class Main {

    /**
     * An example on how to use the library.
     */
    public static void main(String []args){

        //Create the config:
        Config config = new Config();
        config.setEmail("");
        config.setPassword("");
        config.setAndroidID("3db73058499949ce");

        //Set the config:
        PlayStoreManager.init(config);

        //Create the application:
        Application application = new Application("com.looper.apps.loopercalculator");

        //Request the download url:
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

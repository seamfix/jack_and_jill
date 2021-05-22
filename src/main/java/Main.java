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
        config.setEmail("jeffemuveyan@gmail.com");
        config.setPassword("jeff2468JEFF");
        config.setAndroidID("3db73058499949ce");
        config.setToken("9wdH6KAYioNU7gJD7-vGuEqVKyUV9CjRdcn3OvACMAmb6L8qkc8O4Uk79RotSv8pFrm19A.");
        config.setUseragent("Android-Finsky/13.1.32-all (versionCode=81313200,sdk=24,device=dream2lte,hardware=dream2lte,product=dream2ltexx,build=NRD90M:user)");

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

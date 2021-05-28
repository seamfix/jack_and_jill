import constants.Constants;
import manager.PlayStoreManager;
import model.AppDetailsResponse;
import model.Application;
import model.Config;

public class Main {

    /**
     * An example on how to use the library.
     */
    public static void main(String []args){

        //Create the config:
        Config config = new Config();
        config.setEmail(Constants.USERNAME);
        config.setPassword(Constants.PASSWORD);
        config.setAndroidID("3db73058499949ce");

        PlayStoreManager playStoreManager = new PlayStoreManager();
        //Set the config:
        playStoreManager.init(config);

        //Create the application:
        Application application = new Application("com.whatsapp");

        //Request the download url:
        AppDetailsResponse  appDetails = playStoreManager.getAppDetails(application);

        System.out.println(appDetails);

    }
}

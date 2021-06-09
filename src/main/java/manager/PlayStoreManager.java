package manager;

import com.akdeniz.googleplaycrawler.DownloadData;
import com.akdeniz.googleplaycrawler.GooglePlay;
import com.akdeniz.googleplaycrawler.GooglePlayAPI;
import constants.Constants;
import enums.ErrorReason;
import model.AppDetailsResponse;
import model.Application;
import model.Config;
import util.Util;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import static util.Util.extractDownloadUrl;
import static util.Util.isValidPackageName;



public class PlayStoreManager {

    //This should always be  set to 1
    private Config config;


    /**
     * init() should be called first before getDownloadUrl() in order to initialize the GooglePlayAPI
     * class that will be needed to fetch the download link.
     * @param newConfig:  contains params for GooglePlayAPI.
     */
    public void init(Config newConfig){
        config = newConfig;
    }


    /**
     * This should be called after the init().
     * @param application: the application to retrieve download link.
     */
    public AppDetailsResponse getAppDetails(Application application)  {

        AppDetailsResponse appDetailsResponse = new AppDetailsResponse();
        if(config == null) {
            appDetailsResponse.assignResponse(ErrorReason.NO_CONFIG_FOUND);
            return appDetailsResponse;
        }

        String packageName = application.getPackageName();
        if (!isValidPackageName(packageName)) {
            appDetailsResponse.assignResponse(ErrorReason.INVALID_PACKAGE_NAME);
            return appDetailsResponse;
        }

        DownloadData data = doWork(config, packageName.trim());
        if(data == null){
            appDetailsResponse.assignResponse(ErrorReason.GENERAL_ERROR);
            return appDetailsResponse;
        }

        appDetailsResponse.setDownloadUrl(extractDownloadUrl(data));
        appDetailsResponse.assignResponse(ErrorReason.SUCCESS);
        return appDetailsResponse;
    }

    /*** Does the actual work of fetching the download url of the app represented by this packageName.
     * @param packageName: Your app package name.
     * @param config: a callback for success of failure.
     * @throws SQLException
     * @throws IOException
     */
    private DownloadData doWork(Config config, String packageName){

        try {
            GooglePlayAPI api = Util.getGooglePlayAPI(config);
            GooglePlay.DetailsResponse dr = api.details(packageName);
            int versionCode = dr.getDocV2().getDetails().getAppDetails().getVersionCode();
            return api.purchaseAndDeliver(packageName,versionCode, Constants.OFFER_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

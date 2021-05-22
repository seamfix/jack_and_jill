import callback.OperationResponse;
import com.akdeniz.googleplaycrawler.DownloadData;
import com.akdeniz.googleplaycrawler.GooglePlay;
import com.akdeniz.googleplaycrawler.GooglePlayAPI;
import enums.ErrorReason;
import model.Application;
import model.Config;
import util.Util;
import java.io.IOException;
import java.sql.SQLException;
import static util.Util.extractDownloadUrl;
import static util.Util.isValidPackageName;



public class PlayStoreManager {

    //This should always be  set to 1
    final static int OFFER_TYPE = 1;
    private static Config config;


    /**
     * init() should be called first before getDownloadUrl() in order to initialize the GooglePlayAPI
     * class that will be needed to fetch the download link.
     * @param newConfig:  contains params for GooglePlayAPI.
     */
    public static void init(Config newConfig){
        config = newConfig;
    }


    public static void getDownloadUrl(Application application, OperationResponse response){
        if(config != null) {
            String packageName = application.getPackageName();
            if (!isValidPackageName(packageName)) {
                response.error(ErrorReason.INVALID_PACKAGE_NAME.errorReason);
            } else {
                doWork(config, packageName.trim(), response);
            }
        }else{
            response.error(ErrorReason.NO_CONFIG_FOUND.errorReason);
        }
    }


    /*** Does the actual work of fetching the download url of the app represented by this packageName.
     * @param packageName: Your app package name.
     * @param response: a callback for success of failure.
     * @throws SQLException
     * @throws IOException
     */
    private static void doWork(Config config,
                               String packageName,
                               OperationResponse response){

        try {
            GooglePlayAPI api = Util.getGooglePlayAPI(config);
            GooglePlay.DetailsResponse dr = api.details(packageName);
            int versionCode = dr.getDocV2().getDetails().getAppDetails().getVersionCode();
            DownloadData data = api.purchaseAndDeliver(packageName,versionCode,OFFER_TYPE);
            String downloadUrl = extractDownloadUrl(data);
            response.success(downloadUrl);
        } catch (Exception e) {
            e.printStackTrace();
            response.error(ErrorReason.GENERAL.errorReason);
        }
    }
}

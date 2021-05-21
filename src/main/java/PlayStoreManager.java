import callback.OperationResponse;
import com.akdeniz.googleplaycrawler.DownloadData;
import com.akdeniz.googleplaycrawler.GooglePlay;
import com.akdeniz.googleplaycrawler.GooglePlayAPI;
import de.onyxbits.raccoon.db.DatabaseManager;
import de.onyxbits.raccoon.gplay.PlayManager;
import de.onyxbits.raccoon.repo.Layout;
import enums.ErrorReason;
import model.Application;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlayStoreManager {

    //This should always be  set to 1
    final static int OFFER_TYPE = 1;


    public static void getDownloadUrl(Application application, OperationResponse response){
        String packageName = application.getPackageName();
        if(!isValidPackageName(packageName)){
            response.error(ErrorReason.INVALID_PACKAGE_NAME.errorReason);
        }else{
            doWork(packageName.trim(), response);
        }
    }


    /*** Does the actual work of fetching the download url of the app represented by this packageName.
     * @param packageName: Your app package name.
     * @param response: a callback for success of failure.
     * @throws SQLException
     * @throws IOException
     */
    private static void doWork(String packageName, OperationResponse response){

        try {
            DatabaseManager dbm = new DatabaseManager(Layout.DEFAULT.databaseDir);
            PlayManager pm = new PlayManager(dbm);
            GooglePlayAPI api = pm.createConnection();
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

    /**
     *
     * @param packageName: Your app's package name.
     * @return true only if the package name is valid.
     */
    public static boolean isValidPackageName(String packageName){
        return packageName != null && !packageName.equals("");
    }


    /**
     * Use this method to extract out the downoad url from the DownloadData.
     * @param data
     * @return download url.
     */
    public static String extractDownloadUrl(DownloadData data){
        String asText = data.toString();
        Pattern pattern = Pattern.compile("downloadUrl: \"(.*?)\"\ndownloadAuthCookie");//this usually holds the user's name
        Matcher matcher = pattern.matcher(asText);
        String downloadUrl = null;
        while(matcher.find()){
            downloadUrl = matcher.group(1).trim(); //remove any leading or trailing space
        }
        return downloadUrl;
    }
}

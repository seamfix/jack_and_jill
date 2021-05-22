package util;

import com.akdeniz.googleplaycrawler.DownloadData;
import com.akdeniz.googleplaycrawler.GooglePlayAPI;
import model.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    public static GooglePlayAPI getGooglePlayAPI(Config config){

        GooglePlayAPI googlePlayAPI = new GooglePlayAPI(config.getEmail(), config.getPassword());
        googlePlayAPI.setLocalization("en-NG");
        googlePlayAPI.setUseragent(config.getUseragent());
        googlePlayAPI.setAndroidID(config.getAndroidID());
        googlePlayAPI.setToken(config.getToken());

        return googlePlayAPI;
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
     * Use this method to extract out the download url from the DownloadData.
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

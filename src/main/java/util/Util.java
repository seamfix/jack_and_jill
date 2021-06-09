package util;

import com.akdeniz.googleplaycrawler.DownloadData;
import com.akdeniz.googleplaycrawler.GooglePlayAPI;
import de.onyxbits.raccoon.net.DroidConnectionSocketFactory;
import model.Config;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    public static GooglePlayAPI getGooglePlayAPI(Config config) throws Exception {

        GooglePlayAPI googlePlayAPI = new GooglePlayAPI(config.getEmail(), config.getPassword());
        googlePlayAPI.setLocalization("en-NG");
        googlePlayAPI.setAndroidID(config.getAndroidID());
        googlePlayAPI.setClient(createLoginClient());
        googlePlayAPI.login();//this actually sets the token.
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


    protected static HttpClient createLoginClient() {
        RegistryBuilder<ConnectionSocketFactory> rb = RegistryBuilder.create();
        rb.register("https", new DroidConnectionSocketFactory());
        // rb.register("http", new DroidConnectionSocketFactory());
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
                rb.build());
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(30);
        // TODO: Increase the max connection limits. If we are doing bulkdownloads,
        // we will download from multiple hosts.
        int timeout = 9;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        HttpClientBuilder hcb = HttpClientBuilder.create().setDefaultRequestConfig(
                config);

        return hcb.setConnectionManager(connManager).build();
    }
}

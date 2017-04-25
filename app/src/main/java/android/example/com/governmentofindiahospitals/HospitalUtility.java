package android.example.com.governmentofindiahospitals;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shyam on 20/4/17.
 */

public final class HospitalUtility {
    public static final String LOG_TAG = HospitalUtility.class.getSimpleName();
    public static String [][] getHospitalData(String urlRequest){
        String jsonResponse = null;
        try {
            URL url = createUrl(urlRequest);
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG,"Error in reading input stream",e);
        }
        String [][] hospitalData = extractHospitalsDataFromJson(jsonResponse);
        return hospitalData;
    }

    private static String[][] extractHospitalsDataFromJson(String jsonResponse) {

        String [][] hospitalData = null;
        try{
            JSONObject jsonObjectHospital = new JSONObject(jsonResponse);
            JSONArray jsonArrayHospital = jsonObjectHospital.getJSONArray("records");
            hospitalData = new String[jsonArrayHospital.length()][4];
            for(int i = 0; i < jsonArrayHospital.length(); i++){
                JSONObject jsonObject = jsonArrayHospital.getJSONObject(i);
                hospitalData[i] = new String []{jsonObject.getString("StateUT"),jsonObject.getString("SubDivisionalHospitalSDHAsOn31stMarch2014"),jsonObject.getString("DistrictHospitalDHAsOn31stMarch2014"),jsonObject.getString("MobileMedicalUnitsMMUAsOn31stMarch2014")};
            }
        }
        catch (JSONException e){
            Log.e(LOG_TAG,"Error in parsing json.",e);
        }
        return hospitalData;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        HttpsURLConnection urlConnection;
        String jsonResponse = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = getJsonResponse(inputStream);
            }
            else {
                Log.e(LOG_TAG,"Error respond code "+urlConnection.getResponseCode());
            }
        }
        catch (IOException e){
            Log.e(LOG_TAG,"Error in reading input stream.",e);
        }
        return jsonResponse;
    }

    private static String getJsonResponse(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null){
            output.append(line);
            line = bufferedReader.readLine();
        }
        return output.toString();
    }

    private static URL createUrl(String urlRequest) {
        URL url = null;
        try {
            url = new URL(urlRequest);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error in forming requested url",e);
        }
        return url;
    }
}

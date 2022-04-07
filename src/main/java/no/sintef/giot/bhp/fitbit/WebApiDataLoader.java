package no.sintef.giot.bhp.fitbit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import no.sintef.giot.bhp.core.LoadDataService;

public class WebApiDataLoader extends LoadDataService {

	private static final String TAG = "WebApiDataLoader";

    private static final String FITBIT_URL_SLEEP = "https://api.fitbit.com/1.2/user/-/sleep/date/2022-01-01/2022-04-01.json";

    private static final String FITBIT_URL_PROFILE = "https://api.fitbit.com/1/user/-/profile.json";

    private static final String FITBIT_URL_ACTIVITY_INTRADAY_STEPS = "https://api.fitbit.com/1/user/-/activities/steps/date/2022-01-01/2022-04-01/1min.json";

    private final Gson gson = new Gson();

    /**
     * Get data from FitBit Web API
     *
     * @return data from FitBit Web API
     */
    public String getData() {

        // should be a singleton
        OkHttpClient client = new OkHttpClient();
        
        Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("local.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fitbit_access_token = prop.getProperty("fitbit_access_token");

		System.out.println(fitbit_access_token);
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + fitbit_access_token)
                .url(FITBIT_URL_ACTIVITY_INTRADAY_STEPS)
                .build();

        //this is an ugly workaround to write the http response into the result
        final String[] result = {""};

        // Get a handler that can be used to post to the main thread
        try {
			String res = client.newCall(request).execute().body().string();
			System.out.println(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		/*
		 * .enqueue(new Callback() {
		 * 
		 * public void onFailure(Request request, IOException e) { // TODO
		 * Auto-generated method stub //Log.e(TAG, e.getMessage());
		 * 
		 * }
		 * 
		 * public void onResponse(Response response) throws IOException { // TODO
		 * Auto-generated method stub if (!response.isSuccessful()) { throw new
		 * IOException("Unexpected code " + response); } else { //JsonObject json =
		 * JsonParser.parseString(response.body().string()).getAsJsonObject(); //Gson
		 * gson = new GsonBuilder().setPrettyPrinting().create(); //String prettyJson =
		 * gson.toJson(json); //Log.i(TAG, "Result " + prettyJson); //result[0] =
		 * prettyJson;
		 * 
		 * result[0] = response.body().string(); }
		 * 
		 * } });
		 */
        return result[0];
    }

    /**
     * Implements the parent method
     *
     * @return FitBit data in CSV format
     */
    @Override
    public String getDataAsCSV() {
        //TODO
        return null;
    }

    /**
     * Implements the parent method
     *
     * @return FitBit data in Json format
     */
    @Override
    public String getDataAsJson() {
        //no transformation  needed since FitBit data is already in Json
        return getData();
    }

}

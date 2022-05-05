package no.sintef.giot.bhp.fitbit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

	final static Logger logger = Logger.getLogger(WebApiDataLoader.class);

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
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String fitbit_access_token = prop.getProperty("fitbit_access_token");

		Request request = new Request.Builder().header("Authorization", "Bearer " + fitbit_access_token)
				.url(FITBIT_URL_ACTIVITY_INTRADAY_STEPS).build();

		String result = "";

		try {
			Response response = client.newCall(request).execute();

			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);
			} else {
				result = response.body().string();
				JsonObject json = JsonParser.parseString(result).getAsJsonObject();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String prettyJson = gson.toJson(json);
				logger.debug("Result " + prettyJson);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	/**
	 * Implements the parent method
	 *
	 * @return FitBit data in CSV format
	 */
	@Override
	public String getDataAsCSV() {
		// TODO
		return null;
	}

	/**
	 * Implements the parent method
	 *
	 * @return FitBit data in Json format
	 */
	@Override
	public String getDataAsJson() {
		// no transformation needed since FitBit data is already in Json
		return getData();
	}

}

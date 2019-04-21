package com.pickyourtrail.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieMania {

	private final String GET_URL = "https://jsonmock.hackerrank.com/api/movies/search/";

	static int movieIndex = 0;
	static int total = 0;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the movie title: ");
		String query = scanner.next();
		scanner.close();
		MovieMania movieMania = new MovieMania();
		movieMania.getMovieTitles(query);

	}

	public  void getMovieTitles(String query) {

		try {

			String url = appendTitleParam(GET_URL, query);

			int page = 1;

			JSONObject response = getResponseFromUrl(new URL(url));

			if (response != null) {

				total = response.getInt("total");
				String[] titles = new String[total];
				JSONArray movies = response.getJSONArray("data");

				for (int i = 0; i < movies.length(); i++) {

					titles[movieIndex] = ((JSONObject) movies.get(i)).getString("Title");
					++movieIndex;

				}

				while (movieIndex < total) {

					page = page + 1;
					String pageUrl = appendPageParam(url, page);
					parseResponse(getResponseFromUrl(new URL(pageUrl.toString())), titles);

				}

				Arrays.sort(titles);
				System.out.println("Following are the titles in sorted order :");
				for (int i = 0; i < titles.length; i++) {
					System.out.println(titles[i]);
				}

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public String appendTitleParam(String url, String param) {

		return url + "?Title=" + param;

	}

	public String appendPageParam(String url, int param) {

		return url + "&page=" + param;
	}

	public void parseResponse(JSONObject response, String[] titles) throws JSONException {

		if (response != null) {

			JSONArray movies = response.getJSONArray("data");
			for (int i = 0; i < movies.length(); i++) {
				titles[movieIndex] = ((JSONObject) movies.get(i)).getString("Title");
				++movieIndex;

			}

		}

	}

	public JSONObject getResponseFromUrl(URL url) throws IOException, JSONException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");

		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			con.disconnect();

			JSONObject responseJSON = new JSONObject(response.toString());

			return responseJSON;

		} else {
			System.out.println("GET request not worked");
			return null;
		}
	}

}

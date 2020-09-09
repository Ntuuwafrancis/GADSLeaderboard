package com.frank.gadsleaderboard;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ApiUtil {

    private ApiUtil() {}

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    public static final String LEARNING_LEADERS_PATH = "/api/hours";
    public static final String SKILL_IQ_LEADERS_PATH = "/api/skilliq";

    public static URL buildUrl(String path) {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL + path);
        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasNext = scanner.hasNext();
            if (scanner.hasNext()){
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static ArrayList<LearningLeader> getLearningLeadersFromJson(String json) {

        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String BADGE_URL = "badgeUrl";

        ArrayList<LearningLeader> TopLearningLeaders = new ArrayList<>();
        ArrayList<LearningLeader> learningLeaders = new ArrayList<>();

        try {
            JSONArray arrayLearners = new JSONArray(json);
            int numberOfLearners = arrayLearners.length();
            for (int i=0; i<numberOfLearners; i++){
              JSONObject jsonHoursLearner = arrayLearners.getJSONObject(i);
              LearningLeader learningLeader = new LearningLeader(
                      jsonHoursLearner.getString(NAME),
                      jsonHoursLearner.getInt(HOURS),
                      jsonHoursLearner.getString(COUNTRY),
                      jsonHoursLearner.getString(BADGE_URL)

              );


              learningLeaders.add(learningLeader);
              //hours[i] = jsonHoursLearner.getInt(HOURS);

            }

            //Sorting Learners by Hours Consumed in Ascending order
            Collections.sort(learningLeaders, new Comparator<LearningLeader>() {
                @Override
                public int compare(LearningLeader o1, LearningLeader o2) {

                    return Integer.valueOf(o2.hours).compareTo(o1.hours);
                }
            });

            for (int i = 0; i < 20; i++){
                TopLearningLeaders.add(learningLeaders.get(i));
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return TopLearningLeaders;
    }

}

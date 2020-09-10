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

        ArrayList<LearningLeader> topLearningLeaders = new ArrayList<>();
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

            }

            //Sorting Learners by Hours Consumed in Descending order
            Collections.sort(learningLeaders, new Comparator<LearningLeader>() {
                @Override
                public int compare(LearningLeader o1, LearningLeader o2) {

                    return Integer.valueOf(o2.hours).compareTo(o1.hours);
                }
            });

            for (int i = 0; i < 20; i++){
                topLearningLeaders.add(learningLeaders.get(i));
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return topLearningLeaders;
    }

    public static ArrayList<SkillIqLearder> getSkillIqLeadersFromJson (String json) {
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGE_URL = "badgeUrl";

        ArrayList<SkillIqLearder> skillIqLearders = new ArrayList<>();
        ArrayList<SkillIqLearder> topSkillIqLearders = new ArrayList<>();

        try {
            JSONArray arraySkillIq = new JSONArray(json);
            int numberOfLeaders = arraySkillIq.length();
            for (int j = 0; j < numberOfLeaders; j++){
                JSONObject jsonSkillIqLeader = arraySkillIq.getJSONObject(j);
                SkillIqLearder skillIqLearder = new SkillIqLearder(
                        jsonSkillIqLeader.getString(NAME),
                        jsonSkillIqLeader.getInt(SCORE),
                        jsonSkillIqLeader.getString(COUNTRY),
                        jsonSkillIqLeader.getString(BADGE_URL)
                );

                skillIqLearders.add(skillIqLearder);
            }

            //Sorting Skill IQ Learners by Score in Descending order
            Collections.sort(skillIqLearders, new Comparator<SkillIqLearder>() {
                @Override
                public int compare(SkillIqLearder o1, SkillIqLearder o2) {
                    return Integer.valueOf(o2.score).compareTo(o1.score);
                }
            });

            for (int j = 0; j < 20; j++ ) {
                topSkillIqLearders.add(skillIqLearders.get(j));
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return topSkillIqLearders;
    }

}


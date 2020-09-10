package com.frank.gadsleaderboard;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SkillIqLearder {
    public String name;
    public int score;
    public String country;
    public String badgeUrl;

    public SkillIqLearder(String name, int score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public static Drawable loadSkillIqImage (ImageView view, String imageUrl) {
        if (!imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.skill_iq)
                    .into(view);
        } else {
            view.setBackgroundResource(R.drawable.top_learner);
        }
        return view.getDrawable();
    }
}

package com.frank.gadsleaderboard;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LearningLeader {
    public String name;
    public int hours;
    public String country;
    public String badgeUrl;

    public LearningLeader(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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

    public static Drawable loadImage (ImageView view, String imageUrl) {
        if (!imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.top_learner)
                    .into(view);
        } else {
            view.setBackgroundResource(R.drawable.top_learner);
        }
        return view.getDrawable();
    }
}

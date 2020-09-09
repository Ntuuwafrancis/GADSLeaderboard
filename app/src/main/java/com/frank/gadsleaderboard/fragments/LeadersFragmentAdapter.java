package com.frank.gadsleaderboard.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LeadersFragmentAdapter extends FragmentStateAdapter {

    public LeadersFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new LearningLeadersFragment();
            default:
                return new SkillIQLeadersFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}

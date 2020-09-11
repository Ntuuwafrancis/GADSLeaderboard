package com.frank.gadsleaderboard.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frank.gadsleaderboard.ApiUtil;
import com.frank.gadsleaderboard.R;
import com.frank.gadsleaderboard.SkillIqLearder;
import com.frank.gadsleaderboard.SkillIqRecyclerAdapter;

import java.net.URL;
import java.util.ArrayList;

public class SkillIQLeadersFragment extends Fragment {

    View mView;
    URL skillIqLeaderUrl;
    private RecyclerView mRecyclerView;
    private SkillIqRecyclerAdapter mSkillIqRecyclerAdapter;
    private ArrayList<SkillIqLearder> mSkillIqLearders;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

        mRecyclerView = mView.findViewById(R.id.recycler_skill_iq);

        initializeDisplayContent();
        return mView;
    }

    private void initializeDisplayContent() {
        try {
            skillIqLeaderUrl = ApiUtil.buildUrl(ApiUtil.SKILL_IQ_LEADERS_PATH);
            new SkillIqQueryTask().execute(skillIqLeaderUrl);

        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public class SkillIqQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL skillIqUrl = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(skillIqUrl);
            } catch (Exception e) {
                Log.d("error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvError = mView.findViewById(R.id.tv_skill_iq);
            if (result == null) {
                mRecyclerView.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                mRecyclerView.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }
            mSkillIqLearders = ApiUtil.getSkillIqLeadersFromJson(result);
            mSkillIqRecyclerAdapter = new SkillIqRecyclerAdapter(getContext(), mSkillIqLearders);

            mRecyclerView.setAdapter(mSkillIqRecyclerAdapter);
        }
    }


}
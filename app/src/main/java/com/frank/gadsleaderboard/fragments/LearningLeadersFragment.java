package com.frank.gadsleaderboard.fragments;

import android.app.Activity;
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
import com.frank.gadsleaderboard.LearningLeader;
import com.frank.gadsleaderboard.LearningRecyclerAdapter;
import com.frank.gadsleaderboard.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class LearningLeadersFragment extends Fragment {

    View mView;
    URL leaderUrl;
    private RecyclerView mRecyclerView;
    private LearningRecyclerAdapter mLearningAdapter;
    private ArrayList<LearningLeader> mLearningLeaders;

    public LearningLeadersFragment() {
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
        mView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mRecyclerView = mView.findViewById(R.id.recycler_learning);

        initializeDisplayContent();


        return mView;
    }

    public void initializeDisplayContent() {
        try {
            leaderUrl = ApiUtil.buildUrl(ApiUtil.LEARNING_LEADERS_PATH);
            new LeadersQueryTask().execute(leaderUrl);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    public class LeadersQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL leadersURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(leadersURL);
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvError = mView.findViewById(R.id.tv_learning);
            if (result == null) {
                mRecyclerView.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                mRecyclerView.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);

            }
            mLearningLeaders = ApiUtil.getLearningLeadersFromJson(result);
            mLearningAdapter = new LearningRecyclerAdapter(getContext(), mLearningLeaders);

            mRecyclerView.setAdapter(mLearningAdapter);

        }


    }

}
package com.frank.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillIqRecyclerAdapter extends RecyclerView.Adapter<SkillIqRecyclerAdapter.ViewHolder>{

    ArrayList<SkillIqLearder> mSkillIqLearders;
    Context mContext;

    public SkillIqRecyclerAdapter(Context context, ArrayList<SkillIqLearder> skillIqLearders) {
        mContext = context;
        mSkillIqLearders = skillIqLearders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.skill_iq_leader, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillIqLearder skillIqLearder = mSkillIqLearders.get(position);
        holder.bind(skillIqLearder);

    }

    @Override
    public int getItemCount() {
        return mSkillIqLearders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDetails;
        ImageView badge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName_skill_iq);
            tvDetails = itemView.findViewById(R.id.tvDetails_skill_iq);
            badge = itemView.findViewById(R.id.skill_iq_badge);
        }

        public void bind (SkillIqLearder skillIqLearder) {
            tvName.setText(skillIqLearder.getName());
            badge.setImageDrawable(skillIqLearder.loadSkillIqImage(badge, skillIqLearder.badgeUrl));
            tvDetails.setText(new StringBuilder().append(skillIqLearder.getScore()).append(" skill IQ Score, ").append(skillIqLearder.getCountry()).toString());
        }
    }
}

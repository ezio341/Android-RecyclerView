package com.argadiaz.quiz2.eplapp.screens.teamview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.argadiaz.quiz2.eplapp.databinding.ItemTeamBinding;
import com.argadiaz.quiz2.eplapp.models.Team;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<Team> teams;
    private TeamOnClickHandler handler;

    public TeamAdapter(List<Team> teams, TeamOnClickHandler handler) {
        this.teams = teams;
        this.handler = handler;
    }
    public TeamAdapter(TeamOnClickHandler handler){
        this.handler=handler;
    }
    public void setTeamList(List<Team> teams){
        this.teams = teams;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTeamBinding binding = ItemTeamBinding.inflate(inflater, parent, false);
        return new TeamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.bind(team);
    }

    @Override
    public int getItemCount() {
        return teams != null? teams.size() : 0;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        private ItemTeamBinding binding;

        public TeamViewHolder(ItemTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Team team){
            binding.imgTeam.setImageResource(team.getImgId());
            binding.setTeam(team);
            binding.setClickListener(handler);
            binding.executePendingBindings();
        }
    }
}

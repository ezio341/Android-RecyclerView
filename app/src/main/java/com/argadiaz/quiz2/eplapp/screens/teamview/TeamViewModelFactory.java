package com.argadiaz.quiz2.eplapp.screens.teamview;

import com.argadiaz.quiz2.eplapp.models.Team;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TeamViewModelFactory implements ViewModelProvider.Factory {
    private List<Team> team;

    public TeamViewModelFactory(List<Team> team) {
        this.team = team;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TeamViewModel.class)){
            return (T) new TeamViewModel(team);
        }
        throw new IllegalArgumentException("TeamViewModel Required");
    }
}

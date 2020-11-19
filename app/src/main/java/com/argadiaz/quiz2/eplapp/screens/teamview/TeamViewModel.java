package com.argadiaz.quiz2.eplapp.screens.teamview;

import com.argadiaz.quiz2.eplapp.models.Team;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamViewModel extends ViewModel {
    private MutableLiveData<List<Team>> teamMutable = new MutableLiveData<List<Team>>();
    private List<Team> team = null;

    public TeamViewModel(List<Team> team) {
        this.team = team;
    }

    public LiveData<List<Team>> getTeam(){
        return teamMutable;
    }

    private MutableLiveData<Team> _navigateToDetail = new MutableLiveData<>();

    public LiveData<Team> navigateToDetail(){
        return _navigateToDetail;
    }

    public void onTeamClicked(Team team){
        _navigateToDetail.setValue(team);
    }

    public void onTeamDetailNavigated(){
        _navigateToDetail.setValue(null);
    }

}

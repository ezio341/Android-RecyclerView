package com.argadiaz.quiz2.eplapp.screens.teamview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.argadiaz.quiz2.eplapp.R;
import com.argadiaz.quiz2.eplapp.databinding.FragmentTeamBinding;
import com.argadiaz.quiz2.eplapp.models.Player;
import com.argadiaz.quiz2.eplapp.models.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {
    private FragmentTeamBinding binding;
    TeamViewModel viewModel;
    private List<Team> teams;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false);
        dummyDataInsertion();
        TeamViewModelFactory factory = new TeamViewModelFactory(teams);
        viewModel = new ViewModelProvider(this, factory).get(TeamViewModel.class);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvTeam();
    }

    public void setupRvTeam(){
        binding.rvTeam.setLayoutManager(new GridLayoutManager(getContext(), 2));
        TeamAdapter adapter = new TeamAdapter(teams, new TeamOnClickHandler() {
            @Override
            public void onClick(Team team) {
                viewModel.onTeamClicked(team);
            }
        });
        binding.rvTeam.setAdapter(adapter);
        //observe team
        viewModel.getTeam().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                adapter.setTeamList(teams);
            }
        });
        //observe team clicked
        viewModel.navigateToDetail().observe(getViewLifecycleOwner(), new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if(team != null) {
                    NavDirections action = TeamFragmentDirections.actionTeamFragmentToPlayerFragment(team);
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.onTeamDetailNavigated();
                }
            }
        });
    }

    //insert data to the list
    public void dummyDataInsertion(){
        teams = new ArrayList<>();
        List<Player> arsenal = new ArrayList<>();
        arsenal.add(new Player("Mesut Ozil"));arsenal.add(new Player("Pierre"));
        arsenal.add(new Player("David Luis"));arsenal.add(new Player("Nicolas Pepe"));
        arsenal.add(new Player("Alexander Lacazette"));arsenal.add(new Player("Granit Xhaka"));
        arsenal.add(new Player("Ainsley"));arsenal.add(new Player("Reiss Nelson"));
        arsenal.add(new Player("Hector Bellerin"));arsenal.add(new Player("Bernd Leno"));
        arsenal.add(new Player("Willian"));
        teams.add(new Team("Arsenal", arsenal,R.drawable.arsenal));

        List<Player> chelsea = new ArrayList<>();
        chelsea.add(new Player("Olivier giround"));chelsea.add(new Player("Mateo Kovacic"));
        chelsea.add(new Player("Cristian Pulisic"));chelsea.add(new Player("Victor Moses"));
        chelsea.add(new Player("Marcos Alonso"));chelsea.add(new Player("Kepa Arrizabalaga"));
        chelsea.add(new Player("Tammy Abraham"));chelsea.add(new Player("Jorginho"));
        chelsea.add(new Player("Kurt Zouma"));chelsea.add(new Player("Mason Mount"));
        chelsea.add(new Player("Danny Drinkwater"));
        teams.add(new Team("Chelsea", chelsea, R.drawable.chelsea));

        List<Player> manCity = new ArrayList<>();
        manCity.add(new Player("Rahim Steerling"));manCity.add(new Player("Kevin De Bruyne"));
        manCity.add(new Player("Sergio Aguero"));manCity.add(new Player("Riyad Mahrez"));
        manCity.add(new Player("Kyle Walker"));manCity.add(new Player("Fernadinho"));
        manCity.add(new Player("John Stones"));manCity.add(new Player("Bernardo Silva"));
        manCity.add(new Player("Phil Foden"));manCity.add(new Player("Ederson"));
        manCity.add(new Player("Gabriel Jesus"));
        teams.add(new Team("Manchester City", manCity, R.drawable.manchester_city));
    }
}
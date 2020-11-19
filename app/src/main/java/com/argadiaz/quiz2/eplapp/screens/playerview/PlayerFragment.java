package com.argadiaz.quiz2.eplapp.screens.playerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.argadiaz.quiz2.eplapp.R;
import com.argadiaz.quiz2.eplapp.databinding.FragmentPlayerBinding;
import com.argadiaz.quiz2.eplapp.models.Team;

public class PlayerFragment extends Fragment {
    FragmentPlayerBinding binding;
    Team team;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);
        // Inflate the layout for this fragment
        assert getArguments() != null;
        team = PlayerFragmentArgs.fromBundle(getArguments()).getTeam();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setTeam(team);
        setupRvPlayer();
    }

    public void setupRvPlayer(){
        binding.rvPlayer.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPlayer.setAdapter(new PlayerAdapter(team.getPlayerList()));
    }
}
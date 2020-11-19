package com.argadiaz.quiz2.eplapp.screens.playerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.argadiaz.quiz2.eplapp.databinding.ItemPlayerBinding;
import com.argadiaz.quiz2.eplapp.models.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPlayerBinding binding = ItemPlayerBinding.inflate(inflater, parent, false);
        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player, position);
    }

    @Override
    public int getItemCount() {
        return players != null? players.size() : 0;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        ItemPlayerBinding binding;

        public PlayerViewHolder(ItemPlayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Player player, int position){
            binding.setPlayer(player);
            binding.editPlayerNum.setText((position+1)+". ");
            binding.executePendingBindings();
        }
    }
}

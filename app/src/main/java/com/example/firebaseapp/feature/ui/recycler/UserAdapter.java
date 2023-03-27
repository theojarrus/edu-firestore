package com.example.firebaseapp.feature.ui.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.ItemUserBinding;
import com.example.firebaseapp.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> items = new ArrayList<>();
    private final AdapterListener<User> listener;

    public UserAdapter(AdapterListener<User> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<User> items) {
        int size = getItemCount();
        this.items = new ArrayList<>(items);
        notifyItemRangeChanged(0, Math.max(size, getItemCount()));
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {

    private final ItemUserBinding binding;
    private final AdapterListener<User> listener;

    public UserViewHolder(ItemUserBinding binding, AdapterListener<User> listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(User item) {
        binding.name.setText(item.name);
        binding.email.setText(item.email);
        binding.getRoot().setOnClickListener(v -> listener.onEvent(item));
    }
}

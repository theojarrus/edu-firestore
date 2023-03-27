package com.example.firebaseapp.feature.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.firebaseapp.databinding.ActivityMainBinding;
import com.example.firebaseapp.feature.model.Status;
import com.example.firebaseapp.feature.presentation.MainState;
import com.example.firebaseapp.feature.presentation.MainViewModel;
import com.example.firebaseapp.feature.ui.recycler.UserAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new UserAdapter(viewModel::deleteUser);
        binding.recycler.setAdapter(adapter);

        viewModel.state.observe(this, this::render);
        if (savedInstanceState == null) viewModel.observeUsers();
    }

    private void render(MainState state) {
        binding.error.setVisibility(state.getStatus() == Status.ERROR ? View.VISIBLE : View.GONE);
        binding.loading.setVisibility(state.getStatus() == Status.LOADING ? View.VISIBLE : View.GONE);
        binding.recycler.setVisibility(state.getStatus() == Status.SUCCESS ? View.VISIBLE : View.GONE);
        adapter.setItems(state.getUsers());
    }
}

package com.example.firebaseapp.feature.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.domain.repository.UsersRepository;
import com.example.firebaseapp.feature.model.Status;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private MutableLiveData<MainState> _state = new MutableLiveData<>();
    public LiveData<MainState> state = _state;

    public void observeUsers() {
        MainState loadingState = new MainState(Status.LOADING, new ArrayList<>());
        _state.setValue(loadingState);
        UsersRepository.observeUsers(users -> {
            MainState currentState = state.getValue();
            if (currentState != null) {
                currentState.setStatus(Status.SUCCESS);
                currentState.setUsers(users);
                _state.postValue(currentState);
                System.out.println("LOADED");
            } else {
                MainState errorState = new MainState(Status.ERROR, new ArrayList<>());
                _state.postValue(errorState);
            }
        }, exception -> {
            MainState errorState = new MainState(Status.ERROR, new ArrayList<>());
            _state.postValue(errorState);
        });
    }

    public void deleteUser(User user) {
        MainState loadingState = new MainState(Status.LOADING, new ArrayList<>());
        _state.setValue(loadingState);
        UsersRepository.deleteUser(user.id).addOnFailureListener(e -> {
            MainState errorState = new MainState(Status.ERROR, new ArrayList<>());
            _state.postValue(errorState);
        });
    }
}

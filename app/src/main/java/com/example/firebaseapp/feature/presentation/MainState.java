package com.example.firebaseapp.feature.presentation;

import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.feature.model.Status;

import java.util.List;

public class MainState {

    private Status status;
    private List<User> users;

    public MainState(Status status, List<User> users) {
        this.status = status;
        this.users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

package com.example.firebaseapp.domain.repository;

import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.domain.util.DataListener;
import com.example.firebaseapp.domain.util.ErrorListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UsersRepository {

    private static final String STORE_COLLECTION_USERS = "users";

    public static void observeUsers(DataListener<List<User>> listener, ErrorListener errorListener) {
        FirebaseFirestore.getInstance()
                .collection(STORE_COLLECTION_USERS)
                .addSnapshotListener((value, error) -> {
                    if (value != null && error == null) {
                        // do listener.onChange(value.toObjects(User.class));
                        // instead of code below
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                listener.onChange(value.toObjects(User.class));
                            }
                        }.start();
                    } else {
                        errorListener.onError(error);
                    }
                });
    }

    public static Task<Void> deleteUser(String id) {
        return FirebaseFirestore.getInstance()
                .collection(STORE_COLLECTION_USERS)
                .document(id)
                .delete();
    }
}

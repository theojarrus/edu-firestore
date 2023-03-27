package com.example.firebaseapp;

import com.example.firebaseapp.domain.model.Event;
import com.example.firebaseapp.domain.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Example {

    private void example() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("users")
                .document("TURWLIS1pavSxHzBNDGU")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = documentSnapshot.toObject(User.class);
                    System.out.println(user.id);
                    System.out.println(user.name);
                    System.out.println(user.email);
                })
                .addOnFailureListener(Throwable::printStackTrace);

        firestore.collection("users")
                .document("TURWLIS1pavSxHzBNDGU")
                .addSnapshotListener((value, error) -> {
                    if (error == null && value != null && value.exists()) {
                        User user = value.toObject(User.class);
                        System.out.println("DATA LOADED");
                        System.out.println(user.id);
                        System.out.println(user.name);
                        System.out.println(user.email);
                    }
                });

        firestore.collection("users")
                .document("TURWLIS1pavSxHzBNDGU")
                .collection("events")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    List<Event> events = documentSnapshot.toObjects(Event.class);
                    for (Event e : events) {
                        System.out.println(e.name);
                    }
                })
                .addOnFailureListener(Throwable::printStackTrace);

        User user = new User();
        user.id = "iddd";
        user.name = "MobileUser";
        user.email = "usermail@mail.com";

        firestore.collection("users").document("iddd").delete();
    }
}

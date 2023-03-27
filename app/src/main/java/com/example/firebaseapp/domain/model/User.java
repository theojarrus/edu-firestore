package com.example.firebaseapp.domain.model;

import com.google.firebase.firestore.DocumentId;

public class User {
    @DocumentId
    public String id;
    public String name;
    public String email;
}

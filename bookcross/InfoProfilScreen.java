package com.bookcross;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoProfilScreen extends AppCompatActivity { //показывает данные пользователя
    TextView profileName, profileEmail, profilePassword;
    TextView titleName;
    TextView addBook, readBook, comeBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoprofil);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        titleName = findViewById(R.id.titleName);
        addBook = findViewById(R.id.addBook);
        readBook = findViewById(R.id.readBook);
        comeBook = findViewById(R.id.comeBook);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();

            profileName.setText(name);
            profileEmail.setText(email);
            titleName.setText(name);
        }


        String comebook = comeBook.toString();
        String readbook = readBook.toString();
        String addbook = addBook.toString();
       // showInfo();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            addbook = bundle.getString("count");
            readbook = bundle.getString("countread");
            comebook = bundle.getString("countAddBook");
        }
      //  readBook.setText(readbook);
      //  addBook.setText(addbook);
      //  comeBook.setText(comebook);
    }

    public void showInfo() {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//не работает
                User nameUser = dataSnapshot.getValue(User.class);
                User emailUser = dataSnapshot.getValue(User.class);
                User passwordUser = dataSnapshot.getValue(User.class);

                titleName.setText(nameUser.getName());
                profileName.setText(nameUser.getName());
                profileEmail.setText(emailUser.getEmail());
                profilePassword.setText(passwordUser.getPassword());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
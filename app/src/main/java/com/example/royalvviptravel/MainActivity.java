package com.example.royalvviptravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.royalvviptravel.Model.SearchBuses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button search_buses;
    ImageView signOut;
    TextView firstName;
    EditText destination, from, date;

    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference referenceDestination;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String userId;
        if (user != null) {
            userId = user.getUid();
            referenceDestination = database.getReference().child("Users").child(userId);
        }
        reference = database.getReference().child("Users");


        search_buses = findViewById(R.id.search_buses);
        signOut = findViewById(R.id.signOut);
        firstName = findViewById(R.id.Username);
        destination = findViewById(R.id.destination);
        from = findViewById(R.id.from);
        date = findViewById(R.id.date);

        search_buses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchBuses searchBuses = new SearchBuses();
                searchBuses.setDestination(destination.getText().toString());
                searchBuses.setFrom(from.getText().toString());
                searchBuses.setDate(date.getText().toString());

                referenceDestination.child("TripInformation").setValue(searchBuses).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            intent.putExtra("Destination", destination.getText().toString());
                            intent.putExtra("From", from.getText().toString());
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("firstName").getValue() != null){
                        String username = dataSnapshot.child("firstName").getValue().toString();
                        firstName.setText("Hey " + username + "!");
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

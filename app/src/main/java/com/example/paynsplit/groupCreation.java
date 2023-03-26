package com.example.paynsplit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class groupCreation extends AppCompatActivity {


   static String groupID;
   static String userID;
   static String emailID;
   static String Name;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private static final String TAG = "groupCreation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);
        final TextView showgroupid = (TextView)findViewById(R.id.creategroupid);
        final EditText takegroupid = (EditText) findViewById(R.id.joingoupid);
        Button createGroup_button = findViewById(R.id.creategroup);
        Button homepage_button = findViewById(R.id.backtohomepage);
        Button joinGroup_button = findViewById(R.id.joingroup);
        groupID= UUID.randomUUID().toString();



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    userID=user.getUid();
                    emailID=user.getEmail();
                    Name= user.getDisplayName();

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in");

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };
        createGroup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showgroupid.setVisibility(v.getVisibility());
                Toast toast = Toast.makeText(getApplicationContext(), "This is a message displayed in a Toast"+groupID, Toast.LENGTH_SHORT);toast.show();
                showgroupid.setText(groupID);


            }
        });

        joinGroup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                groupID=takegroupid.getText().toString();

                toastMessage("groupid"+groupID);

                            }
        });
        homepage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               userTransaction user = new userTransaction(
                       userID,
                       groupID,
                       emailID



                    );

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(groupCreation.this, "Registration successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(groupCreation.this,welcome.class);
                            startActivity(intent);

                        } else {
                            //display a failure message
                        }
                    }
                });


            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}

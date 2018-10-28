package com.example.usama.cloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button mLogoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mAuth = FirebaseAuth.getInstance();

        mLogoutBtn = (Button) findViewById(R.id.button4);

        mLogoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                mAuth.signOut();
                LoginManager.getInstance().logOut();
            }
        });
    } @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
        {
            updateUI();
        }
    }
    private void updateUI()
    {
        Toast.makeText(AccountActivity.this, "You are logged out.",Toast.LENGTH_SHORT);
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}

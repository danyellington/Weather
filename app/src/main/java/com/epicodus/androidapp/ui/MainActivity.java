package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.locationButton)
    Button mLocationButton;
    @BindView(R.id.savedLocationsButton) Button mSavedLocationsButton;
//    @BindView(R.id.globalButton)
//    ImageView mGlobalButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Typeface rubikFont = Typeface.createFromAsset(getAssets(), "fonts/rubik.ttf");
        

        //mSavedLocationsButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                } else {

                }
            }
        };        mLocationButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString().trim().replaceAll("\\s", "_");
                if (!location.equals("")) {
                    Intent intent = new Intent(MainActivity.this, LocalActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }


                mSavedLocationsButton.setOnClickListener(this);
                if (v == mSavedLocationsButton) {
                    Intent intent = new Intent(MainActivity.this, SavedLocationsActivity.class);
                    startActivity(intent);
                }


            }
        });

//        ImageView Button = (ImageView) findViewById(R.id.globalButton);
//        Button.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse("https://www.accuweather.com/en/world-weather"));
//                startActivity(intent);
//            }
//
//        });


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }




//        EditText Location = (EditText) findViewById(R.id.locationEditText);
//        Intent i = new Intent(MainActivity.this, LocalActivity.class);
//        i.putExtra("location", Location.getText().toString());
//        startActivity(i);


    }







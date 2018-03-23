package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.locationButton)
    Button mLocationButton;
    @BindView(R.id.globalButton)
    ImageView mGlobalButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.textView3)
    TextView mTextView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface rubikFont = Typeface.createFromAsset(getAssets(), "fonts/rubik.ttf");
        mTextView.setTypeface(rubikFont);
        mTextView2.setTypeface(rubikFont);
        mTextView3.setTypeface(rubikFont);
        mLocationButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString().trim().replaceAll("\\s", "_");
                if (!location.equals("")) {
                    Intent intent = new Intent(MainActivity.this, LocalActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }


            }
        });

        ImageView Button = (ImageView) findViewById(R.id.globalButton);
        Button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.accuweather.com/en/world-weather"));
                startActivity(intent);
            }

        });


//        EditText Location = (EditText) findViewById(R.id.locationEditText);
//        Intent i = new Intent(MainActivity.this, LocalActivity.class);
//        i.putExtra("location", Location.getText().toString());
//        startActivity(i);

    }
}






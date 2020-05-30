package com.example.royalvviptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    ImageView back;
    TextView from, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third);

        back = findViewById(R.id.back);
        from = findViewById(R.id.from);
        destination = findViewById(R.id.destination);

        Intent tripInformation = getIntent();
        String fromInfo = tripInformation.getStringExtra("From");
        String destinationInfo = tripInformation.getStringExtra("To");

        from.setText(fromInfo);
        destination.setText(destinationInfo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

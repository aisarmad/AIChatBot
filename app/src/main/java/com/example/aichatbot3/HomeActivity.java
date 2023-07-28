// Home Activity
package com.example.aichatbot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the layout element
        RelativeLayout homeLayout = findViewById(R.id.homeLayout);

        // Set a touch listener for the layout
        homeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Call the method to start the persona selection activity
                startPersonaSelectionActivity();
                return true;
            }
        });
    }

    // Method to start the persona selection activity
    private void startPersonaSelectionActivity() {
        // Create an intent to navigate to the persona selection activity
        Intent intent = new Intent(HomeActivity.this, PersonaSelectionActivity.class);
        startActivity(intent);
        finish();
    }
}

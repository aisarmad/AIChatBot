// Persona Selection Activity
package com.example.aichatbot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PersonaSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_selection);

        // Find the buttons for each persona
        Button buttonPersona1 = findViewById(R.id.buttonPersona1);
        Button buttonPersona2 = findViewById(R.id.buttonPersona2);
        Button buttonPersona3 = findViewById(R.id.buttonPersona3);
        Button buttonPersona4 = findViewById(R.id.buttonPersona4);

        // Set click listeners for each button
        buttonPersona1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the NerdBotActivity
                Intent intent = new Intent(PersonaSelectionActivity.this, NerdBotActivity.class);
                startActivity(intent);
            }
        });

        buttonPersona2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the CleverBotActivity
                Intent intent = new Intent(PersonaSelectionActivity.this, CleverBotActivity.class);
                startActivity(intent);
            }
        });

        buttonPersona3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the DumbBotActivity
                Intent intent = new Intent(PersonaSelectionActivity.this, DumbBotActivity.class);
                startActivity(intent);
            }
        });

        buttonPersona4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the ComedianBotActivity
                Intent intent = new Intent(PersonaSelectionActivity.this, ComedianBotActivity.class);
                startActivity(intent);
            }
        });
    }
}

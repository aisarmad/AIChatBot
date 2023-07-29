// Main Activity
package com.example.aichatbot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView chatTextView;
    private EditText userInputEditText;
    private Button sendButton;
    private String selectedPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        chatTextView = findViewById(R.id.chatTextView);
        userInputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);

        // Retrieve the selected persona from the intent extra
        Intent intent = getIntent();
        if (intent != null) {
            selectedPersona = intent.getStringExtra("persona");
        }

        // Set button click listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user's input message
                String userMessage = userInputEditText.getText().toString();

                // Display the user's message in the chat view
                displayMessage("User: " + userMessage);

                // Forward the user's input to the corresponding persona activity
                if (selectedPersona != null) {
                    if (selectedPersona.equals("NerdBot")) {
                        Intent nerdBotIntent = new Intent(MainActivity.this, NerdBotActivity.class);
                        nerdBotIntent.putExtra("userMessage", userMessage);
                        startActivity(nerdBotIntent);
                    } else if (selectedPersona.equals("CleverBot")) {
                        // Create an intent to navigate to the CleverBotActivity
                        Intent cleverBotIntent = new Intent(MainActivity.this, CleverBotActivity.class);
                        cleverBotIntent.putExtra("userMessage", userMessage);
                        startActivity(cleverBotIntent);
                    } else if (selectedPersona.equals("DumbBot")) {
                        // Create an intent to navigate to the DumbBotActivity
                        Intent dumbBotIntent = new Intent(MainActivity.this, DumbBotActivity.class);
                        dumbBotIntent.putExtra("userMessage", userMessage);
                        startActivity(dumbBotIntent);
                    } else if (selectedPersona.equals("ComedianBot")) {
                        // Create an intent to navigate to the ComedianBotActivity
                        Intent comedianBotIntent = new Intent(MainActivity.this, ComedianBotActivity.class);
                        comedianBotIntent.putExtra("userMessage", userMessage);
                        startActivity(comedianBotIntent);
                    }
                }

                // Clear the input text field
                userInputEditText.setText("");
            }
        });
    }

    // Display a message in the chat view
    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }
}

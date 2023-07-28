// ComedianBotActivity.java
package com.example.aichatbot3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ComedianBotActivity extends AppCompatActivity {
    private TextView chatTextView;
    private EditText userInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements specific to ComedianBot
        TextView personaTextView = findViewById(R.id.personaTextView);
        personaTextView.setText("ComedianBot");

        // Update prompts specific to ComedianBot
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText("Tell me a joke!");

        // Initialize the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Find the chatTextView and userInputEditText
        chatTextView = findViewById(R.id.chatTextView);
        userInputEditText = findViewById(R.id.inputEditText);

        // Set button click listener
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the user's message in the chat view
                displayMessage("User: " + userInputEditText.getText().toString());

                // Process the user's message
                processUserMessage(userInputEditText.getText().toString(), "comedian");

                // Clear the input text field
                userInputEditText.setText("");
            }
        });
    }

    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }

    private void processUserMessage(String userMessage, String persona) {
        if (persona.equalsIgnoreCase("comedian")) {
            String comedianResponse = generateComedianResponse(userMessage);
            displayMessage("ComedianBot: " + comedianResponse);
        }
    }

    private String generateComedianResponse(String userMessage) {
        String comedianResponse = "";

        // Add your logic here to generate a comedian response based on the user's message
        if (userMessage.contains("joke")) {
            comedianResponse = "Sure, here's a classic one: Why don't scientists trust atoms? Because they make up everything! Haaa haaa, got you!";
        } else {
            comedianResponse = "You're setting me up for some great punchlines! But seriously, let's have some fun!";
        }

        return comedianResponse;
    }


}

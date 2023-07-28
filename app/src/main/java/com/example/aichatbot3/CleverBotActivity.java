// CleverBotActivity.java
package com.example.aichatbot3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CleverBotActivity extends AppCompatActivity {
    private TextView chatTextView;
    private EditText userInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements specific to CleverBot
        TextView personaTextView = findViewById(R.id.personaTextView);
        personaTextView.setText("CleverBot");

        // Update prompts specific to CleverBot
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText("Ask me a clever question!");

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
                processUserMessage(userInputEditText.getText().toString(), "clever");

                // Clear the input text field
                userInputEditText.setText("");
            }
        });
    }

    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }

    private void processUserMessage(String userMessage, String persona) {
        if (persona.equalsIgnoreCase("clever")) {
            String cleverResponse = generateCleverResponse(userMessage);
            displayMessage("CleverBot: " + cleverResponse);
        }
    }

    private String generateCleverResponse(String userMessage) {
        String cleverResponse = "";

        // Add your logic here to generate a clever response based on the user's message
        if (userMessage.contains("joke")) {
            cleverResponse = "Why don't scientists trust atoms? Because they make up everything!";
        } else if (userMessage.contains("riddle")) {
            cleverResponse = "I have cities, but no houses. I have mountains, but no trees. I have water, but no fish. What am I? A map!";
        } else {
            cleverResponse = "Brilliant question! Let me astound you with my cleverness.";
        }

        return cleverResponse;
    }


}

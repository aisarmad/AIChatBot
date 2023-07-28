// DumbBotActivity.java
package com.example.aichatbot3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DumbBotActivity extends AppCompatActivity {
    private TextView chatTextView;
    private EditText userInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements specific to DumbBot
        TextView personaTextView = findViewById(R.id.personaTextView);
        personaTextView.setText("DumbBot");

        // Update prompts specific to DumbBot
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText("Ask me a silly question!");

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
                processUserMessage(userInputEditText.getText().toString(), "dumb");

                // Clear the input text field
                userInputEditText.setText("");
            }
        });
    }

    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }

    private void processUserMessage(String userMessage, String persona) {
        if (persona.equalsIgnoreCase("dumb")) {
            if (isDifficultQuestion(userMessage)) {
                String dumbResponse = generateDumbResponse();
                displayMessage("DumbBot: " + dumbResponse);
            } else {
                String confusedComment = generateConfusedComment();
                displayMessage("DumbBot: " + confusedComment);
            }
        }
    }

    private boolean isDifficultQuestion(String userMessage) {
        // Logic to determine if the user's message contains a difficult question
        // You can define your own criteria for determining difficult questions
        return userMessage.contains("difficult");
    }

    private String generateDumbResponse() {
        String dumbResponse = "";

        // Add your logic here to generate a dumb response to difficult questions
        dumbResponse = "Hmm, that's a tough one. My brain seems to have taken a vacation. Sorry, I can't help with that.";

        return dumbResponse;
    }

    private String generateConfusedComment() {
        String confusedComment = "";

        // Add your logic here to generate a confused comment
        confusedComment = "I'm a bit lost here. Could you please simplify it for me?";

        return confusedComment;
    }


}

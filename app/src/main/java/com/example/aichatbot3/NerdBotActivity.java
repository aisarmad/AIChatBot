// NerdBotActivity.java
package com.example.aichatbot3;

import com.android.volley.toolbox.JsonObjectRequest;

import android.util.Log;

import com.android.volley.Response;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;

import com.android.volley.Request;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NerdBotActivity extends AppCompatActivity {

    private TextView chatTextView;
    private EditText userInputEditText;
    private RequestQueue requestQueue;
    private final int MAX_TOKENS = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements specific to NerdBot
        TextView personaTextView = findViewById(R.id.personaTextView);
        personaTextView.setText("NerdBot");

        // Update prompts specific to NerdBot
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText("Ask me a nerdy question!");

        // Initialize the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Find the chatTextView and userInputEditText
        chatTextView = findViewById(R.id.chatTextView);
        userInputEditText = findViewById(R.id.inputEditText);

        // Set button click listener
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display user's input in the chatTextView
                displayMessage("User: " + userInputEditText.getText().toString());

                // Process user's input and get a nerdy response from the chatbot
                processUserMessage(userInputEditText.getText().toString());

                // Clear the input field after processing
                userInputEditText.setText("");
            }
        });
    }

    // Display a message in the chatTextView
    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }

    // Process user's input and get a response from the chatbot
    private void processUserMessage(String userMessage) {
        // Prepare the messages list
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a nerdy assistant (i.e. you always respond like a typical nerd character.");
        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("role", "user");
        userMessageMap.put("content", userMessage);
        messages.add(systemMessage);
        messages.add(userMessageMap);

        // Prepare the request payload
        JSONObject payload = new JSONObject();
        try {
            payload.put("model", "gpt-3.5-turbo");
            payload.put("messages", new JSONArray(messages));
            payload.put("temperature", 0.7);
            payload.put("max_tokens", MAX_TOKENS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the API request using Volley
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://api.openai.com/v1/chat/completions", payload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Get the chatbot's response from the API response
                            JSONArray choices = response.getJSONArray("choices");
                            if (choices.length() > 0) {
                                JSONObject chatbotResponse = choices.getJSONObject(0).getJSONObject("message");
                                String content = chatbotResponse.getString("content");
                                // Process the chatbot's response as a nerd character
                                String processedResponse = processPersonaResponse(content);
                                // Display the nerdy response in the chatTextView
                                displayMessage("NerdBot: " + processedResponse);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                // Set the Authorization header with your API key
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + OPENAI_API_KEY);
                return headers;
            }
        };

        // Add the request to the RequestQueue
        requestQueue.add(request);
    }

    // Process the chatbot's response as a nerd character
    private String processPersonaResponse(String response) {
        // Apply nerd persona logic
        if (response.contains("Fascinating")) {
            response = response.replace("Fascinating", "Ah, fascinating!");
        } else if (response.contains("Intriguing")) {
            response = response.replace("Intriguing", "Intriguing indeed!");
        }
        return response;
    }
}

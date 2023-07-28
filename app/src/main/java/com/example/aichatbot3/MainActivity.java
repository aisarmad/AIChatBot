// Main Activity
package com.example.aichatbot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView chatTextView;
    private EditText userInputEditText;
    private Button sendButton;
    private RequestQueue requestQueue;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String OPENAI_API_KEY = "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";
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

        // Initialize the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Set button click listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user's input message
                String userMessage = userInputEditText.getText().toString();

                // Display the user's message in the chat view
                displayMessage("User: " + userMessage);

                // Process the user's message
                processUserMessage(userMessage);

                // Clear the input text field
                userInputEditText.setText("");
            }
        });
    }

    // Display a message in the chat view
    private void displayMessage(String message) {
        chatTextView.append(message + "\n");
    }

    // Process the user's message using the OpenAI API
    private void processUserMessage(final String userMessage) {
        // Prepare the messages list
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("role", "user");
        userMessageMap.put("content", userMessage);
        messages.add(userMessageMap);

        // Prepare the request payload
        JSONObject payload = new JSONObject();
        try {
            payload.put("model", "gpt-3.5-turbo");
            payload.put("messages", new JSONArray(messages));
            payload.put("temperature", 0.7);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the API request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, OPENAI_API_URL, payload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray choices = response.getJSONArray("choices");
                            if (choices.length() > 0) {
                                JSONObject chatbotResponse = choices.getJSONObject(0).getJSONObject("message");
                                String content = chatbotResponse.getString("content");
                                displayMessage("Chatbot: " + content);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
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
}

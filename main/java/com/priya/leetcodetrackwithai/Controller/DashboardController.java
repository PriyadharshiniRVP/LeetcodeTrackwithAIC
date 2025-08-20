package com.priya.leetcodetrackwithai.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DashboardController {

    @FXML
    private Label easyLabel;

    @FXML
    private Label mediumLabel;

    @FXML
    private Label hardLabel;

    public void initialize() {
        try {
            String username = "PriyadharshiniRV"; // 👈 Your LeetCode username
            String query = "{ \"query\": \"query { matchedUser(username: \\\"" + username + "\\\") { submitStats { acSubmissionNum { difficulty count } } } }\" }";

            URL url = new URL("https://leetcode.com/graphql");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(query.getBytes());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            JSONArray stats = json.getJSONObject("data")
                    .getJSONObject("matchedUser")
                    .getJSONObject("submitStats")
                    .getJSONArray("acSubmissionNum");

            int easy = 0, medium = 0, hard = 0;
            for (int i = 0; i < stats.length(); i++) {
                JSONObject obj = stats.getJSONObject(i);
                switch (obj.getString("difficulty")) {
                    case "Easy": easy = obj.getInt("count"); break;
                    case "Medium": medium = obj.getInt("count"); break;
                    case "Hard": hard = obj.getInt("count"); break;
                }
            }

            easyLabel.setText("Easy: " + easy);
            mediumLabel.setText("Medium: " + medium);
            hardLabel.setText("Hard: " + hard);

        } catch (Exception e) {
            easyLabel.setText("Error fetching stats");
            mediumLabel.setText("");
            hardLabel.setText("");
            e.printStackTrace();
        }
    }
}


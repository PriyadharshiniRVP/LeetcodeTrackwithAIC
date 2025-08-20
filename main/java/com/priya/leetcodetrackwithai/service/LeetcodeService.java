package com.priya.leetcodetrackwithai.service;

import java.io.*;
import java.net.*;
import org.json.*;

public class LeetcodeService {

    public JSONObject fetchStats(String username) throws IOException {
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
        return new JSONObject(response.toString());
    }
}
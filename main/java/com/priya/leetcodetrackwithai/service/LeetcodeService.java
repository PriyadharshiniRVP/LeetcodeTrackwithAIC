package com.priya.leetcodetrackwithai.service;

import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import org.json.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;

@Service
public class LeetcodeService {

    public LeetcodeStats getStats(String username) throws IOException {
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

        // ✅ Check if user exists
        if (!json.has("data") || json.getJSONObject("data").isNull("matchedUser")) {
            throw new IOException("LeetCode user not found: " + username);
        }

        JSONObject matchedUser = json.getJSONObject("data").getJSONObject("matchedUser");
        JSONObject submitStats = matchedUser.optJSONObject("submitStats");
        if (submitStats == null || !submitStats.has("acSubmissionNum")) {
            throw new IOException("No submission stats found for user: " + username);
        }

        JSONArray statsArray = submitStats.getJSONArray("acSubmissionNum");

        LeetcodeStats stats = new LeetcodeStats();
        for (int i = 0; i < statsArray.length(); i++) {
            JSONObject obj = statsArray.getJSONObject(i);
            String difficulty = obj.getString("difficulty");
            int count = obj.getInt("count");

            switch (difficulty.toLowerCase()) {
                case "easy": stats.setEasySolved(count); break;
                case "medium": stats.setMediumSolved(count); break;
                case "hard": stats.setHardSolved(count); break;
            }
        }

        int total = stats.getEasySolved() + stats.getMediumSolved() + stats.getHardSolved();
        stats.setTotalSolved(total);

        return stats;
    }
}

package com.priya.leetcodetrackwithai.service;

import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.priya.leetcodetrackwithai.model.LeetcodeStats;

@Service
public class LeetCodeFetcher {

    public LeetcodeStats fetchStats(String username) {
        String graphqlQuery = "{ \"query\": \"query getUserProfile($username: String!) { matchedUser(username: \\\"" + username + "\\\") { submitStats { acSubmissionNum { difficulty count } } } }\", \"variables\": { \"username\": \\\"" + username + "\\\" } }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("https://leetcode.com/graphql", entity, String.class);

        JSONObject obj = new JSONObject(response.getBody());
        JSONArray statsArray = obj.getJSONObject("data")
                .getJSONObject("matchedUser")
                .getJSONObject("submitStats")
                .getJSONArray("acSubmissionNum");

        int easy = 0, medium = 0, hard = 0;
        for (int i = 0; i < statsArray.length(); i++) {
            JSONObject stat = statsArray.getJSONObject(i);
            String difficulty = stat.getString("difficulty");
            int count = stat.getInt("count");

            switch (difficulty) {
                case "Easy": easy = count; break;
                case "Medium": medium = count; break;
                case "Hard": hard = count; break;
            }
        }

        return new LeetcodeStats(easy + medium + hard, easy, medium, hard);
    }
}

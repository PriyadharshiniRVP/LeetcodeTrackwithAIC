package com.priya.leetcodetrackwithai.Controller;

import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import com.priya.leetcodetrackwithai.service.LeetcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // ✅ This line enables CORS for all frontend origins
@RestController
@RequestMapping("/api")
public class LeetCodeController {

    @Autowired
    private LeetcodeService leetcodeService;

    @GetMapping("/fetchStats")
    public ResponseEntity<LeetcodeStats> fetchStats(@RequestParam String username) {
        try {
            LeetcodeStats stats = leetcodeService.getStats(username);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/aiInsights")
    public ResponseEntity<String> getInsights(@RequestParam String username) {
        try {
            LeetcodeStats stats = leetcodeService.getStats(username);

            int easy = stats.getEasySolved();
            int medium = stats.getMediumSolved();
            int hard = stats.getHardSolved();
            int total = easy + medium + hard;

            if (total == 0) {
                return ResponseEntity.ok("No data found. Start solving problems to get insights!");
            }

            StringBuilder feedback = new StringBuilder("🧠 Here's your progress analysis:\n\n");

            if (easy > medium)
                feedback.append("✅ You're strong in Easy problems. Try focusing more on Medium-level challenges.\n");

            if (hard > 0)
                feedback.append("🔥 You've already tackled some Hard problems — impressive!\n");

            if (medium == 0)
                feedback.append("📌 Consider practicing Medium-level DP or Tree problems next.\n");

            if (total > 100)
                feedback.append("🚀 You're on fire! Keep pushing toward 300+ problems.\n");

            feedback.append("\nKeep going! You're building something amazing.");

            return ResponseEntity.ok(feedback.toString());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating insights.");
        }
    }
}

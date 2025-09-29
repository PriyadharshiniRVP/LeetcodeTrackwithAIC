package com.priya.leetcodetrackwithai.model;

public class LeetcodeStats {
    private int totalSolved;
    private int easySolved;
    private int mediumSolved;
    private int hardSolved;

    // No-arg constructor
    public LeetcodeStats() {}

    // All-arg constructor
    public LeetcodeStats(int totalSolved, int easySolved, int mediumSolved, int hardSolved) {
        this.totalSolved = totalSolved;
        this.easySolved = easySolved;
        this.mediumSolved = mediumSolved;
        this.hardSolved = hardSolved;
    }

    // Getters and setters
    public int getTotalSolved() { return totalSolved; }
    public void setTotalSolved(int totalSolved) { this.totalSolved = totalSolved; }

    public int getEasySolved() { return easySolved; }
    public void setEasySolved(int easySolved) { this.easySolved = easySolved; }

    public int getMediumSolved() { return mediumSolved; }
    public void setMediumSolved(int mediumSolved) { this.mediumSolved = mediumSolved; }

    public int getHardSolved() { return hardSolved; }
    public void setHardSolved(int hardSolved) { this.hardSolved = hardSolved; }
}

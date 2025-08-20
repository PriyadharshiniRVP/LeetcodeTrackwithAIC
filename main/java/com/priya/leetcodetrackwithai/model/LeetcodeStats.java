package com.priya.leetcodetrackwithai.model;


public class LeetcodeStats {
    private String problem;
    private String status;

    public LeetcodeStats(String problem, String status) {
        this.problem = problem;
        this.status = status;
    }

    public String getProblem() {
        return problem;
    }

    public String getStatus() {
        return status;
    }
}


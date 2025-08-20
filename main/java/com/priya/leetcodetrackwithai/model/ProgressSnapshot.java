package com.priya.leetcodetrackwithai.model;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ProgressSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate date;
    private int solvedCount;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getSolvedCount() { return solvedCount; }
    public void setSolvedCount(int solvedCount) { this.solvedCount = solvedCount; }
}

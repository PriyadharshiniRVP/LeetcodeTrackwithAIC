package com.priya.leetcodetrackwithai;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXDashboard extends Application {
    public static void main(String[] args) {
        launch(args); // This triggers the JavaFX lifecycle
    }

    @Override
    public void start(Stage primaryStage) {
        // Your UI setup here
        primaryStage.setTitle("LeetCode Tracker");
        primaryStage.show();
    }
}

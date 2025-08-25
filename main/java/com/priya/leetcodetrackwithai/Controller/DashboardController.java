package com.priya.leetcodetrackwithai.Controller;

import com.priya.leetcodetrackwithai.service.LeetCodeFetcher;
import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.chart.PieChart;
import org.json.JSONObject;

public class DashboardController {

    @FXML private TableView<LeetcodeStats> tableView;
    @FXML private TableColumn<LeetcodeStats, String> keyColumn;
    @FXML private TableColumn<LeetcodeStats, String> valueColumn;
    @FXML private PieChart progressChart;
    @FXML private Label insightLabel;

    @FXML
    public void initialize() {
        keyColumn.setCellValueFactory(data -> data.getValue().keyProperty());
        valueColumn.setCellValueFactory(data -> data.getValue().valueProperty());
        refreshStats(); // Load initial stats
    }

    @FXML
    public void refreshStats() {
        try {
            JSONObject stats = LeetCodeFetcher.fetchStats("PriyadharshiniRV");

            int total = stats.getInt("totalSolved");
            int easy = stats.getInt("easySolved");
            int medium = stats.getInt("mediumSolved");
            int hard = stats.getInt("hardSolved");

            // Table data
            ObservableList<LeetcodeStats> statsList = FXCollections.observableArrayList(
                    new LeetcodeStats("Total Solved", String.valueOf(total)),
                    new LeetcodeStats("Easy", String.valueOf(easy)),
                    new LeetcodeStats("Medium", String.valueOf(medium)),
                    new LeetcodeStats("Hard", String.valueOf(hard))
            );
            tableView.setItems(statsList);

            // PieChart data
            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                    new PieChart.Data("Easy", easy),
                    new PieChart.Data("Medium", medium),
                    new PieChart.Data("Hard", hard)
            );
            progressChart.setData(chartData);
            progressChart.setTitle("Problem Difficulty Breakdown");

            // Insight label
            String insight = String.format(
                    "You've solved %d problems: %.1f%% Easy, %.1f%% Medium, %.1f%% Hard.",
                    total,
                    (easy * 100.0 / total),
                    (medium * 100.0 / total),
                    (hard * 100.0 / total)
            );
            insightLabel.setText(insight);

        } catch (Exception e) {
            tableView.setItems(FXCollections.observableArrayList(
                    new LeetcodeStats("Error", "Unable to fetch data")
            ));
            insightLabel.setText("Something went wrong while fetching stats.");
            progressChart.setData(FXCollections.observableArrayList());
            e.printStackTrace();
        }
    }
}

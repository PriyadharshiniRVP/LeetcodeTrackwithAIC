package com.priya.leetcodetrackwithai.Controller;

import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DashboardController {

    @FXML
    private TableView<LeetcodeStats> tableView;

    @FXML
    private TableColumn<LeetcodeStats, String> labelColumn;

    @FXML
    private TableColumn<LeetcodeStats, String> valueColumn;

    @FXML
    public void initialize() {
        labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        try {
            JSONObject stats = fetchLeetcodeStats("PriyadharshiniRV"); // ← Replace this

            ObservableList<LeetcodeStats> statsList = FXCollections.observableArrayList(
                    new LeetcodeStats("Total Solved", String.valueOf(stats.getInt("totalSolved"))),
                    new LeetcodeStats("Easy", String.valueOf(stats.getInt("easySolved"))),
                    new LeetcodeStats("Medium", String.valueOf(stats.getInt("mediumSolved"))),
                    new LeetcodeStats("Hard", String.valueOf(stats.getInt("hardSolved")))
            );

            tableView.setItems(statsList);
        } catch (Exception e) {
            tableView.setItems(FXCollections.observableArrayList(
                    new LeetcodeStats("Error", "Unable to fetch data")
            ));
            e.printStackTrace();
        }
    }

    private JSONObject fetchLeetcodeStats(String username) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://leetcode-stats-api.herokuapp.com/" + username))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}

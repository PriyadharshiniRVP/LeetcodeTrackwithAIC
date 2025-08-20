package com.priya.leetcodetrackwithai.Controller;



import com.priya.leetcodetrackwithai.model.LeetcodeStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONObject;

public class DashboardController {

    @FXML private TableView<LeetcodeStats> tableView;
    @FXML private TableColumn<LeetcodeStats, String> labelColumn;
    @FXML private TableColumn<LeetcodeStats, String> valueColumn;

    @FXML
    public void initialize() {
        labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.setItems(fetchLiveStats("PriyadharshiniRV")); // Replace with your LeetCode username
    }

    private ObservableList<LeetcodeStats> fetchLiveStats(String username) {
        ObservableList<LeetcodeStats> stats = FXCollections.observableArrayList();

        try {
            String url = "https://leetcode-api-faisalshohag.vercel.app/user/" + username;
            String response = Request.get(url).execute().returnContent().asString();
            JSONObject json = new JSONObject(response).getJSONObject("user");

            stats.add(new LeetcodeStats("Total Solved", String.valueOf(json.getInt("totalSolved"))));
            stats.add(new LeetcodeStats("Easy Solved", String.valueOf(json.getInt("easySolved"))));
            stats.add(new LeetcodeStats("Medium Solved", String.valueOf(json.getInt("mediumSolved"))));
            stats.add(new LeetcodeStats("Hard Solved", String.valueOf(json.getInt("hardSolved"))));
            stats.add(new LeetcodeStats("Ranking", String.valueOf(json.getInt("ranking"))));
            stats.add(new LeetcodeStats("Contribution Points", String.valueOf(json.getInt("contributionPoints"))));

        } catch (Exception e) {
            stats.add(new LeetcodeStats("Error", "Unable to fetch data"));
            e.printStackTrace();
        }

        return stats;
    }
}

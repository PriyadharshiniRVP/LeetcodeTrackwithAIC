package com.priya.leetcodetrackwithai.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LeetcodeStats {
    private final StringProperty label;
    private final StringProperty value;

    public LeetcodeStats(String label, String value) {
        this.label = new SimpleStringProperty(label);
        this.value = new SimpleStringProperty(value);
    }

    public String getLabel() {
        return label.get();
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public StringProperty valueProperty() {
        return value;
    }
}

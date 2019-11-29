package com.nycjv321.hackathon.models;

import java.util.List;

public class Dataset {
    private String label;
    private List<Long> data;

    Dataset(String label, List<Long> data) {
        this.label = label;
        this.data = data;
    }

    public static Dataset create(String label, List<Long> data) {
        return new Dataset(label, data);
    }

    public String getLabel() {
        return label;
    }

    public List<Long> getData() {
        return data;
    }
}

package com.nycjv321.hackathon;

import com.nycjv321.hackathon.models.Dataset;

import java.util.Arrays;

public interface TestData {

    Dataset dataSetFor2017 = Dataset.create("2017", Arrays.asList(10L, 20L, 30L, 40L, 50L, 60L, 70L));
    Dataset dataSetFor2018 = Dataset.create("2018", Arrays.asList(8L, 9L, -10L, 10L, 40L, 60L, 40L));
    Dataset dataSetFor2019 = Dataset.create("2019", Arrays.asList(5L, 10L, 15L, 20L, 25L, 30L, 35L));

}

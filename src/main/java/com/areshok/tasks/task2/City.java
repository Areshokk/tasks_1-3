package com.areshok.tasks.task2;

import java.util.ArrayList;
import java.util.List;

public class City {

    String name;
    List<Edge> neighbors;

    public City(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Edge neighbor) {
        neighbors.add(neighbor);
    }
}

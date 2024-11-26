package com.areshok.tasks.task2;

import java.util.*;

public class Main {
    public static Map<String, Integer> cityIndexMap = new HashMap<>();
    public static List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // Number of test cases
        sc.nextLine();

        while (t-- > 0) {
            // Reset city data for each test case
            cityIndexMap.clear();
            cities.clear();

            int n = sc.nextInt();  // Number of cities
            sc.nextLine();

            // Read all cities and their connections
            for (int i = 0; i < n; i++) {
                String cityName = sc.nextLine().trim();
                cityIndexMap.put(cityName, i + 1);
                City city = new City(cityName);
                cities.add(city);

                int p = sc.nextInt();  // Number of neighbors for this city
                sc.nextLine();

                for (int j = 0; j < p; j++) {
                    String[] connection = sc.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(connection[0]);
                    int cost = Integer.parseInt(connection[1]);
                    city.addNeighbor(new Edge(neighborIndex, cost));
                }
            }

            // Read and process the queries
            int r = sc.nextInt();  // Number of queries
            sc.nextLine();

            for (int i = 0; i < r; i++) {
                String[] query = sc.nextLine().split(" ");
                String name1 = query[0];
                String name2 = query[1];

                int sourceIndex = cityIndexMap.get(name1);
                int destIndex = cityIndexMap.get(name2);

                int minCost = dijkstra(sourceIndex, destIndex, n);
                System.out.println(minCost);
            }
        }

        sc.close();
    }

    // Dijkstra's algorithm.
    static int dijkstra(int sourceIndex, int destIndex, int n) {
        // Create an array to store the shortest distance from the source city to every other city
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[sourceIndex] = 0;

        // Use a priority queue to keep track of which city to process next
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // Start with the source city at a distance of 0
        pq.add(new int[]{sourceIndex, 0});

        while (!pq.isEmpty()) {
            // Get the city with the smallest distance from the queue
            int[] current = pq.poll();
            int u = current[0];          // The current city index
            int currentDist = current[1]; // The distance to this city

            // If we found a shorter way to this city skip it.
            if (currentDist > dist[u]) {
                continue;
            }

            // Look at all the neighboring cities of the current city
            for (Edge e : cities.get(u - 1).neighbors) {
                int v = e.neighborIndex;  // The neighbor city index
                int weight = e.cost;      // The cost to travel to this neighbor

                // Check if we found a quicker way to reach this neighbor through the current city
                if (dist[u] + weight < dist[v]) {
                    // Update the shortest distance to this neighbor
                    dist[v] = dist[u] + weight;
                    // Add the neighbor to the queue to explore it later
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // Once we've checked all possible paths return the distance to the destination city
        // If the destination city is still at "infinity" it means there's no path
        return dist[destIndex] == Integer.MAX_VALUE ? -1 : dist[destIndex];
    }

}

package src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsProcessor {

    private List<Result> results = new ArrayList<>();

    public void loadResults(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String gender = data[1].trim();
                String distance = data[2].trim();
                String time = data[3].trim();
                results.add(new Result(name, gender, distance, time));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Result> getTopRunners(int distance, String gender, int n) {
        return results.stream()
                .filter(result -> result.getDistance().equals(distance + " км") && result.getGender().equals(gender))
                .sorted(Comparator.comparing(Result::getTime))
                .limit(n)
                .collect(Collectors.toList());
    }

    public void printRunners(List<Result> topResults) {
        for (Result result : topResults) {
            System.out.println("Имя: " + result.getName() + ", пол: " + result.getGender() +
                    ", дистанция: " + result.getDistance() + ", время: " + result.getTime());
        }
    }
}

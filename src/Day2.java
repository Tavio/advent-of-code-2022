import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 {
    private static final Map<String, Integer> shapeScores = Map.of(
            "X", 1,
            "Y", 2,
            "Z", 3
    );

    private static final Map<String, Map<String, Integer>> roundScores = Map.of(
            "A", Map.of(
                    "X", 3,
                    "Y", 6,
                    "Z", 0),
            "B", Map.of(
                    "X", 0,
                    "Y", 3,
                    "Z", 6),
            "C", Map.of(
                    "X", 6,
                    "Y", 0,
                    "Z", 3)
    );

    private static final Map<String, Map<String, String>> plan = Map.of(
            "A", Map.of(
                    "X", "Z",
                    "Y", "X",
                    "Z", "Y"),
            "B", Map.of(
                    "X", "X",
                    "Y", "Y",
                    "Z", "Z"),
            "C", Map.of(
                    "X", "Y",
                    "Y", "Z",
                    "Z", "X")
    );

    private static Integer calculateRoundScore(String[] round) {
        return roundScores.get(round[0]).get(round[1]) + shapeScores.get(round[1]);
    }

    private static String calculateShouldPlay(String[] round) {
        return plan.get(round[0]).get(round[1]);
    }
    private static void part1() throws Exception {
        List<String[]> strategy = parseInput();
        Integer finalScore = strategy.stream()
                .map(round -> calculateRoundScore(round))
                .reduce(0, Integer::sum);
        System.out.println(finalScore);
    }

    private static void part2() throws Exception {
        List<String[]> strategy = parseInput();
        Integer finalScore = strategy.stream()
                .map(round -> new String[]{round[0], calculateShouldPlay(round)})
                .map(round -> calculateRoundScore(round))
                .reduce(0, Integer::sum);
        System.out.println(finalScore);
    }

    private static List<String[]> parseInput() throws IOException {
        URL url = Day2.class.getResource("day2.txt");
        List<String[]> strategy = Files.lines(Paths.get(URLDecoder.decode(url.getPath(), "UTF-8")))
                .map(line -> line.trim().split(" "))
                .collect(Collectors.toList());
        return strategy;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
}

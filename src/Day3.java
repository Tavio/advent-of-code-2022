import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {

    private static int getPriority(Character item) {
        if(Character.isLowerCase(item)) {
            return ((int) item) - 96;
        } else {
            return ((int) item) - 64 + 26;
        }
    }

    private static List<List<Character[]>> getElfGroups(List<Character[]> objs) {
        return new ArrayList<>(IntStream.range(0, objs.size()).boxed().collect(
                Collectors.groupingBy(e -> e / 3, Collectors.mapping(objs::get, Collectors.toList())
                )).values());
    }

    private static void part1() throws Exception {
        var rucksacks = parseInput();
        var prioritySum = 0;
        for(var rucksack : rucksacks) {
            Character[] firstCompartment = Arrays.copyOfRange(rucksack, 0, rucksack.length / 2);
            Character[] secondCompartment = Arrays.copyOfRange(rucksack, rucksack.length / 2, rucksack.length);
            Set<Character> firstCompartmentSet = new HashSet<>();
            Set<Character> secondCompartmentSet = new HashSet<>();
            Collections.addAll(firstCompartmentSet, firstCompartment);
            Collections.addAll(secondCompartmentSet, secondCompartment);
            Set<Character> intersection = new HashSet<>(firstCompartmentSet);
            intersection.retainAll(secondCompartmentSet);
            var incorrectElement = intersection.iterator().next();
            prioritySum += getPriority(incorrectElement);
        }
        System.out.println(prioritySum);
    }

    private static void part2() throws Exception {
        var rucksacks = parseInput();
        var elfGroups = getElfGroups(rucksacks);
        var prioritySum = 0;
        for(var elfGroup : elfGroups) {
            Set<Character> firstGroupSet = new HashSet<>();
            Set<Character> secondGroupSet = new HashSet<>();
            Set<Character> thirdGroupSet = new HashSet<>();
            Collections.addAll(firstGroupSet, elfGroup.get(0));
            Collections.addAll(secondGroupSet, elfGroup.get(1));
            Collections.addAll(thirdGroupSet, elfGroup.get(2));
            Set<Character> intersection = new HashSet<>(firstGroupSet);
            intersection.retainAll(secondGroupSet);
            intersection.retainAll(thirdGroupSet);
            var badge = intersection.iterator().next();
            prioritySum += getPriority(badge);
        }
        System.out.println(prioritySum);
    }

    private static List<Character[]> parseInput() throws IOException {
        URL url = Day3.class.getResource("day3.txt");
        List<Character[]> strategy = Files.lines(Paths.get(URLDecoder.decode(url.getPath(), "UTF-8")))
                .map(line -> line.chars().mapToObj(i->(char)i).toArray(Character[]::new))
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

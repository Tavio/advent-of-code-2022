import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    private static void part1() throws Exception {
        List<Integer> calories = parseInput();
        Integer max = Collections.max(calories);
        System.out.println(max);
    }

    private static void part2() throws Exception {
        List<Integer> calories = parseInput();
        Integer maxCalory1 = 0;
        Integer maxCalory2 = 0;
        Integer maxCalory3 = 0;

        for(Integer calory : calories) {
            if(calory > maxCalory1) {
                Integer temp = maxCalory1;
                Integer temp2 = maxCalory2;
                maxCalory1 = calory;
                maxCalory2 = temp;
                maxCalory3 = temp2;
            } else if(calory > maxCalory2) {
                Integer temp = maxCalory2;
                maxCalory2 = calory;
                maxCalory3 = temp;
            } else if(calory > maxCalory3) {
                maxCalory3 = calory;
            }
        }
        System.out.println(maxCalory1 + maxCalory2 + maxCalory3);
    }

    private static List<Integer> parseInput() throws IOException {
        URL url = Day1.class.getResource("day1.txt");
        List<String> lines = Files.lines(Paths.get(URLDecoder.decode(url.getPath(), "UTF-8")))
                .collect(Collectors.toList());
        List<Integer> calories = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++) {
            Integer nextCalory = 0;
            while(i < lines.size() && ! "".equals(lines.get(i))) {
                nextCalory += Integer.parseInt(lines.get(i));
                i++;
            }
            calories.add(nextCalory);
        }
        return calories;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
}

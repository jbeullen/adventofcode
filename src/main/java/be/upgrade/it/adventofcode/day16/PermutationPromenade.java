package be.upgrade.it.adventofcode.day16;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PermutationPromenade {
    public static final String INPUT_FILE = "day16.txt";
    public static final String INITIAL_SEQUENCE = "abcdefghijklmnop";
    public static final int ITERATIONS = 1000000000;

    public static void main(String[] args) {
        PermutationPromenade permutationPromenade = new PermutationPromenade();
        String result1  = permutationPromenade.calculatePart1();
        System.out.println("Part 1: result: "+result1);
        String result2  = permutationPromenade.calculatePart2();
        System.out.println("Part 2: result: "+result2);
    }

    //TODO: refactor + unit test
    public String calculatePart1() {
        String current = getInitialSequence();
        List<String> permutations = getPermutations();
        for (String permutation : permutations) {
            String newSequence = executePermutationOnSequence(current, permutation);
            current = newSequence;
        }
        return current;
    }

    public String calculatePart2() {
        String current = getInitialSequence();
        List<String> permutations = getPermutations();
        boolean first = true;
        for (int i = 999999971; i < ITERATIONS; i++) {
            int j = 0;
            for (String permutation : permutations) {
                if(!first || j > 9999) {
                    String newSequence = executePermutationOnSequence(current, permutation);
                    current = newSequence;
                }
                j++;
            }
            first = false;
        }
        return current;
    }

    public String executePermutationOnSequence(String sequence, String permutation) {
        String operator = StringUtils.substring(permutation, 0, 1);
        String payload = StringUtils.substring(permutation, 1);
        String[] args = StringUtils.split(payload, "/");
        switch (operator){
            case "s":
                int steps = Integer.parseInt(payload);
                return performSpin(sequence, steps);

            case "x":
                int position1 = Integer.parseInt(args[0]);
                int position2 = Integer.parseInt(args[1]);
                return performExchange(sequence, position1, position2);
            case "p":
                char c1 = args[0].charAt(0);
                char c2 = args[1].charAt(0);
                return performPartner(sequence, c1, c2);
            default:
                System.out.println("Unknown operator: "+operator+": doing nothing");
                return sequence;
        }
    }

    public String performPartner(String sequence, char c1, char c2) {
        int position1 = sequence.indexOf(c1);
        int position2 = sequence.indexOf(c2);

        char[] chars = sequence.toCharArray();

        chars[position1] = c2;
        chars[position2] = c1;

        return new String(chars);
    }

    public String performExchange(String sequence, int position1, int position2) {
        char[] chars = sequence.toCharArray();

        char c1 = sequence.charAt(position1);
        char c2 = sequence.charAt(position2);

        chars[position1] = c2;
        chars[position2] = c1;

        return new String(chars);
    }

    public String performSpin(String sequence, int steps) {
        char[] chars = sequence.toCharArray();
        char[] range1 = Arrays.copyOfRange(chars, 0, chars.length - steps);
        char[] range2 = Arrays.copyOfRange(chars, chars.length - steps, chars.length);

        return new String(range2)+new String(range1);
    }

    public String getInitialSequence() {
        return INITIAL_SEQUENCE;
    }

    public List<String> getPermutations(){
        List<String> permutations = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(INPUT_FILE).toURI()))) {

            stream
                    .forEach(s -> {
                        String[] split = StringUtils.split(s, ",");
                        Arrays.stream(split).forEach(s1 -> permutations.add(StringUtils.trim(s1)));
                    });

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return permutations;
    }
}

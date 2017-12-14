package be.upgrade.it.adventofcode.day7;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class BottomProgram {
    public static final String INPUT_FILE = "day7_1.txt";

    public static void main(String [ ] args) {
        Set<String> notTopLevelPropgrams = new HashSet<>();


        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(INPUT_FILE).toURI()))) {

            stream
                .filter(s -> StringUtils.contains(s,"->"))
                .map( s -> StringUtils.substringAfter(s, "->"))
                .forEach(s -> {
                    String[] split = StringUtils.split(s, ",");
                    Arrays.stream(split).forEach(s1 -> notTopLevelPropgrams.add(StringUtils.trim(s1)));
                });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //notTopLevelPropgrams.stream().forEach(s -> System.out.println(s));


        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(INPUT_FILE).toURI()))) {

            stream
                    .filter(s -> StringUtils.contains(s,"->"))
                    .map( s -> StringUtils.trim(StringUtils.substringBefore(s, "(")))
                    .filter(s -> !notTopLevelPropgrams.contains(s))
                    .forEach(s -> System.out.println(s));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

package be.upgrade.it.adventofcode.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trampolines {
    //TODO: unit test
    private static final String INPUT_FILE = "day5.txt";
    private List<Integer> moves;
    private int index = 0;
    private int steps = 0;

    public Trampolines(List<Integer> moves){
        this.moves = moves;
    }

    public int calculateStepsPart1(){
        while(index < moves.size()) {
            int i = moves.get(index);
            moves.set(index, i+1);
            index = index + i;
            steps++;
        }
        return steps;
    }

    public int calculateStepsPart2(){
        while(index < moves.size()) {
            int i = moves.get(index);
            if(i >= 3){
                moves.set(index, i-1);
            } else {
                moves.set(index, i+1);
            }

            index = index + i;
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        Trampolines trampolines1 = new Trampolines(loadMoves());
        int steps1 = trampolines1.calculateStepsPart1();
        System.out.println("part 1: steps: "+steps1);
        Trampolines trampolines2 = new Trampolines(loadMoves());
        int steps2 = trampolines2.calculateStepsPart2();
        System.out.println("part 2: steps: "+steps2);

    }

    public static List<Integer> loadMoves(){
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(INPUT_FILE).toURI()))) {

            return stream.map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("problem loading file: "+INPUT_FILE);
        }
    }
}

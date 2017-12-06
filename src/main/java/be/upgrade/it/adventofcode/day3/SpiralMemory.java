package be.upgrade.it.adventofcode.day3;

import java.util.ArrayList;
import java.util.List;

public class SpiralMemory {
    private static final int INPUT = 289326;

    public static void main(String [ ] args) {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(INPUT);
        System.out.println("Steps: "+i);

    }

    public int calculateSteps(int input) {
        List<Integer> xIndex = xIndex(input);
        List<Integer> yIndex = yIndex(input);


        int x = xIndex.get(xIndex.size() - 1);
        if(x < 0){
            x = -x;
        }
        int y = yIndex.get(yIndex.size() - 1);
        if(y < 0){
            y = -y;
        }
        return x + y;
    }

    private List<Integer> yIndex(int input) {
        // 0 0 1 1 1 0 -1 -1 -1 -1 0 1 2 2 2 2 2
        List<Integer> index = new ArrayList<>();
        index.add(0);

        boolean forward = true;
        int current = 0;
        int max = 1;
        int repeat = 2;

        int repeated = 0;

        for (int i = 0; i < input-1; i++) {
            //System.out.println("current: "+current+" max: "+max+" forward: "+forward);
            index.add(current);
            if(current == max || current == -max ){
                if(repeated == repeat) {
                    forward = !forward;
                    if(current == -max){
                        max++;
                    }
                    repeated = 0;
                    repeat++;
                    if(forward){
                        current++;
                    } else {
                        current--;
                    }
                } else {
                    repeated++;
                }
            } else {
                if(forward){
                    current++;
                } else {
                    current--;
                }
            }
        }

        return index;
    }
    private List<Integer> xIndex(int input){
        List<Integer> index = new ArrayList<>();
        boolean forward = true;
        int current = 0;
        int max = 1;
        int repeat = 1;

        int repeated = 0;

        for (int i = 0; i < input; i++) {
            //System.out.println("current: "+current+" max: "+max+" forward: "+forward);
            index.add(current);
            if(current == max || current == -max ){
                if(repeated == repeat) {
                    forward = !forward;
                    if(current == -max){
                        max++;
                    }
                    repeated = 0;
                    repeat++;
                    if(forward){
                        current++;
                    } else {
                        current--;
                    }
                } else {
                    repeated++;
                }
            } else {
                if(forward){
                    current++;
                } else {
                    current--;
                }
            }
        }

        return index;
    }
}

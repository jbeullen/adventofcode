package be.upgrade.it.adventofcode.day15;

import org.apache.commons.lang3.StringUtils;

public class DuelingGenerators {
    public static final long ITERATIONS_PART1 = 40000000;
    public static final long ITERATIONS_PART2 = 5000000;
    public static final long SEED_GENERATOR_A = 783;
    public static final long SEED_GENERATOR_B = 325;
    public static final long MULTIPLIER_GENERATOR_A = 16807;
    public static final long MULTIPLIER_GENERATOR_B = 48271;
    public static final long CHECKER_GENERATOR_A = 4;
    public static final long CHECKER_GENERATOR_B = 8;
    public static final long DIVIDER = 2147483647;

    public static void main(String[] args) {
        //TODO: refactor + Unit Test
        DuelingGenerators duelingGenerators = new DuelingGenerators();
        long count1 = duelingGenerators.calculateMatchesPart1(SEED_GENERATOR_A, SEED_GENERATOR_B, MULTIPLIER_GENERATOR_A, MULTIPLIER_GENERATOR_B, DIVIDER, ITERATIONS_PART1);
        System.out.println("Count1: "+count1);
        long count2 = duelingGenerators.calculateMatchesPart2(SEED_GENERATOR_A, SEED_GENERATOR_B, MULTIPLIER_GENERATOR_A, MULTIPLIER_GENERATOR_B, CHECKER_GENERATOR_A , CHECKER_GENERATOR_B, DIVIDER, ITERATIONS_PART2);
        System.out.println("Count2: "+count2);
    }

    public long calculateMatchesPart1(long seed1, long seed2, long multiplier1, long multiplier2, long divider, long iterations){
        long counter = 0;
        long l1 = seed1;
        long l2 = seed2;

        for (int i = 0; i < iterations; i++) {
            if(doLowestSixteenBitsMatch(l1,l2)){
                counter++;
            }
            l1 = calculateNextValue(l1, multiplier1, divider);
            l2 = calculateNextValue(l2, multiplier2, divider);
        }

        return counter;
    }

    public long calculateMatchesPart2(long seed1, long seed2, long multiplier1, long multiplier2, long checker1, long checker2, long divider, long iterations){
        long counter = 0;
        long l1 = isDividableBy(seed1, checker1)? seed1 : calculateNextDividableValue(seed1, multiplier1, divider, checker1);
        long l2 = isDividableBy(seed2, checker2)? seed2 : calculateNextDividableValue(seed2, multiplier2, divider, checker2);

        for (int i = 0; i < iterations; i++) {
            if(doLowestSixteenBitsMatch(l1,l2)){
                counter++;
            }
            l1 = calculateNextDividableValue(l1, multiplier1, divider, checker1);
            l2 = calculateNextDividableValue(l2, multiplier2, divider, checker2);
        }

        return counter;
    }

    public static boolean doLowestSixteenBitsMatch(long l1, long l2){
        String s1 = convertToLowestSixteenBitsString(l1);
        String s2 = convertToLowestSixteenBitsString(l2);
        return StringUtils.equals(s1,s2);
    }

    public static String convertToLowestSixteenBitsString(long l){
        String s = Long.toBinaryString(l);
        for (int i = s.length(); i < 32 ; i++) {
            s = "0"+s;
        }
        return StringUtils.substring(s, 16);
    }

    public static long calculateNextValue(long current, long multiplier, long divider){
        return (current * multiplier) % divider;
    }

    public static boolean isDividableBy(long current, long divider){
        return (current % divider) == 0;
    }

    public static long calculateNextDividableValue(long current, long multiplier, long divider, long checkDivider){
        long l = calculateNextValue(current, multiplier, divider);
        while(!isDividableBy(l, checkDivider)){
            l = calculateNextValue(l, multiplier, divider);
        }
        return l;
    }
}

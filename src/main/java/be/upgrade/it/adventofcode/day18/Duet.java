package be.upgrade.it.adventofcode.day18;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Duet {
    //TODO: refactor and unit test
    private static final String INPUT_FILE = "day18.txt";

    private Map<String, Long> registers = new HashMap<>();
    private List<String[]> operations;
    private int operationIndex = 0;
    private long lastPlayedSoundValue = 0;
    private boolean recovered = false;
    private boolean jumped = false;


    private Duet(List<String[]> operations){
        this.operations = operations;
    }

    private long processToRecover(){
        while (!recovered){
            performOperation(operations.get(operationIndex));
            if(jumped){
                jumped = false;
            } else {
                operationIndex++;
            }
        }
        return lastPlayedSoundValue;
    }

    private void performOperation(String[] operation){
        String op = operation[0];
        String registerKey = operation[1];
        switch (op){
            case "set":
                performSet(registerKey, operation[2]);
                break;
            case "snd":
                performPlaySound(registerKey);
                break;
            case "add":
                performAdd(registerKey, operation[2]);
                break;
            case "mul":
                performMultiply(registerKey, operation[2]);
                break;
            case "mod":
                performModulo(registerKey, operation[2]);
                break;
            case "jgz":
                performJump(registerKey, operation[2]);
                break;
            case "rcv":
                performRecover(registerKey);
                break;
            default:
                System.out.println("Unknown operation: "+op+": doing nothing");
        }
    }

    private void performRecover(String registerKey) {
        if(getRegisterValue(registerKey) != 0){
            recovered = true;
        }
    }

    private void performJump(String registerKey, String payload) {
        if(getRegisterValue(registerKey) > 0) {
            operationIndex = operationIndex + (int) getValueForPayload(payload);
            jumped = true;
        }
    }

    private void performModulo(String registerKey, String payload) {
        registers.put(registerKey, getRegisterValue(registerKey) % getValueForPayload(payload));
    }

    private long getValueForPayload(String payload){
        try {
            return Long.parseLong(payload);
        } catch (NumberFormatException e) {
            return getRegisterValue(payload);
        }
    }

    private long getRegisterValue(String key){
        if(registers.containsKey(key)){
            return registers.get(key);
        }
        return 0L;
    }

    private void performMultiply(String registerKey, String payload) {
        registers.put(registerKey, getRegisterValue(registerKey) * getValueForPayload(payload));
    }

    private void performAdd(String registerKey, String payload) {
        registers.put(registerKey, getRegisterValue(registerKey) + getValueForPayload(payload));
    }

    private void performPlaySound(String registerKey) {
        lastPlayedSoundValue = getRegisterValue(registerKey);
    }

    private void performSet(String registerKey, String payload){
        registers.put(registerKey, getValueForPayload(payload));
    }

    private static List<String[]> loadOperations(){
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(INPUT_FILE).toURI()))) {

            return stream.map(s -> StringUtils.split(s, " ")).collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("problem loading file: "+INPUT_FILE);
        }
    }

    public static void main(String [ ] args) {
        Duet duet = new Duet(loadOperations());
        long l = duet.processToRecover();
        System.out.println("part 1: "+l);

    }
}

package cellular.automaton;

import java.util.Arrays;

public class Main {

    private static final int RULE = 54;
    private static final int GENERATIONS = 17;
    private static final int GRID_WIDTH = 35;

    public static void main(String[] args) {
        Calculator calculator = new Calculator(RULE);
        System.out.println("RULE = " + RULE + ": " + Arrays.toString(calculator.getRuleArray()));
        new Printer(calculator).print(createGeneration0(), GENERATIONS);
    }

    private static int[] createGeneration0() {
        int[] generation0 = new int[GRID_WIDTH];
        generation0[GRID_WIDTH / 2] = 1;
        return generation0;
    }
}

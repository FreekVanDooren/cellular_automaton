package cellular.automaton;

import java.util.Arrays;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class Main {

    private static final int RULE = 54;
    private static final int GENERATIONS = 17;
    private static final int GRID_WIDTH = 35;
    private static final int[] RULE_ARRAY = asBinaryArray(RULE);

    private static int[] asBinaryArray(int currentNr) {
        int[] output = new int[8];
        for (int i = output.length - 1; i >= 0; i--) {
            int digit = (int) Math.pow(2, i);
            if (digit <= currentNr) {
                output[i] = 1;
                currentNr -= digit;
            }
        }
        if (currentNr != 0) {
            throw new IllegalStateException("How can there be a remainder of: " + currentNr);
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println("RULE = " + RULE + ": " + Arrays.toString(RULE_ARRAY));
        int[] generation0 = new int[GRID_WIDTH];
        generation0[GRID_WIDTH / 2] = 1;
        nextGen(generation0, GENERATIONS);
    }

    private static void nextGen(int[] generation, int generations) {
        print(generation);
        if (generations == 0) {
            return;
        }
        int[] next = range(0, GRID_WIDTH).map(index -> {
            int lhs = index == 0 ? 0 : generation[index - 1];
            int rhs = index == GRID_WIDTH - 1 ? 0 : generation[index + 1];
            return newCell(lhs, generation[index], rhs);
        }).toArray();
        nextGen(next, --generations);
    }

    private static void print(int[] generation) {
        System.out.println(
                stream(generation)
                        .mapToObj(cell -> cell == 1 ? "*" : "-")
                        .collect(joining(" "))
        );
    }

    private static int newCell(int lhs, int mid, int rhs) {
        int idx = lhs << 2 | mid << 1 | rhs;
        return RULE_ARRAY[idx];
    }
}

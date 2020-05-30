package cellular.automaton;

import static java.util.stream.IntStream.range;

public class Calculator {
    private final int[] ruleArray;

    public Calculator(int rule) {
        this.ruleArray = asBinaryArray(rule);
    }

    private static int[] asBinaryArray(int ruleNr) {
        if(ruleNr < 0 || ruleNr > 255){
            throw new IllegalArgumentException("Cannot handle rule < 0 or rule > 255, this is: " + ruleNr);
        }
        int currentNr = ruleNr;
        int[] output = new int[8];
        for (int i = output.length - 1; i >= 0; i--) {
            int digit = (int) Math.pow(2, i);
            if (digit <= currentNr) {
                output[i] = 1;
                currentNr -= digit;
            }
        }
        return output;
    }

    int[] getRuleArray() {
        return this.ruleArray;
    }

    public int[] nextGeneration(int[] currentGeneration) {
        int gridWidth = currentGeneration.length;
        return range(0, gridWidth).map(index ->
                newCell(
                        index == 0 ? 0 : currentGeneration[index - 1],
                        currentGeneration[index],
                        index == gridWidth - 1 ? 0 : currentGeneration[index + 1]
                )
        ).toArray();
    }

    private Integer newCell(int lhs, int mid, int rhs) {
        int index = lhs << 2 | mid << 1 | rhs;
        return this.ruleArray[index];
    }
}

package cellular.automaton;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class Printer {
    private final Calculator calculator;

    public Printer(Calculator calculator) {
        this.calculator = calculator;
    }

    public void print(int[] currentGeneration, int generations) {
        if (generations < 0) {
            return;
        }
        print(currentGeneration);
        print(this.calculator.nextGeneration(currentGeneration), --generations);
    }


    private static void print(int[] generation) {
        System.out.println(
                stream(generation)
                        .mapToObj(cell -> cell == 1 ? "*" : "-")
                        .collect(joining(" "))
        );
    }
}

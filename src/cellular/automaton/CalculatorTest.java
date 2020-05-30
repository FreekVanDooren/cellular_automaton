package cellular.automaton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @DisplayName("Rule out of bounds")
    @ParameterizedTest(name = "{arguments} is not a rule")
    @ValueSource(ints = {-1, 256})
    void ruleNrOutOfBounds(int rule) {
        assertThrows(IllegalArgumentException.class, () -> new Calculator(rule));
    }

    @DisplayName("Rule array")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ruleArrayExpectations")
    void getRuleArray(int rule, int[] expected) {
        assertArrayEquals(expected, new Calculator(rule).getRuleArray());
    }

    private static Stream<Arguments> ruleArrayExpectations() {
        return Stream.of(
                Arguments.of(0, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
                Arguments.of(54, new int[]{0, 1, 1, 0, 1, 1, 0, 0}),
                Arguments.of(255, new int[]{1, 1, 1, 1, 1, 1, 1, 1})
        );
    }

    @DisplayName("Calculate next generation")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("nextGenerationExpectations")
    void nextGeneration(int[] currentGeneration, int[] expected) {
        assertArrayEquals(expected, new Calculator(54).nextGeneration(currentGeneration));
    }

    private static Stream<Arguments> nextGenerationExpectations() {
        return Stream.of(
                Arguments.of(new int[]{0, 0, 1, 0, 0}, new int[]{0, 1, 1, 1, 0}),
                Arguments.of(new int[]{0, 1, 1, 1, 0}, new int[]{1, 0, 0, 0, 1}),
                Arguments.of(new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 0, 1, 1}),
                Arguments.of(new int[]{1, 1, 0, 1, 1}, new int[]{0, 0, 1, 0, 0})
        );
    }
}
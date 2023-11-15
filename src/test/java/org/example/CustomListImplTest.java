package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CustomListImplTest {
    private CustomListImpl customListImpl;

    @BeforeEach
    public void setUp() {
        customListImpl = new CustomListImpl(5);
    }

    static Stream<Arguments> argumentsForTests = Stream.of(Arguments.of("qwerty"),
            Arguments.of("asdfg"),
            Arguments.of("zxcvb"));

}

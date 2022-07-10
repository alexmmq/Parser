import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserFinalTest {
    @BeforeAll
    public static void init(){
        RequestParserFinal requestParser = new RequestParserFinal();
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    public void input_shouldResultInExpectedOutput(String one,String two ) throws IOException {
        String userInput = one;
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        String expected = two;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        RequestParserFinal.main(null);
        String actual = baos.toString();
        assertEquals(expected, actual);
    }
    static Stream<Arguments> stringProvider(){
        return Stream.of(
                Arguments.arguments("http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo",
                        "obj name \ndouble: 3.14\n"),
                Arguments.arguments("http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo",
                        "lvl view name "),
                Arguments.arguments("https://www.google.com/search?channel=fs&client=ubuntu&q=escape+characters+java",
                        "channel client q "),
                Arguments.arguments("http://javarush.ru/alpha/index.html?obj=dell&name=Amigo",
                        "obj name \nString: dell\n")
        );
    }

    @AfterAll
    public static void tearDown(){
        System.setIn(System.in);
        System.setOut(System.out);
    }

}
package data;

import org.junit.Assert;
import org.junit.Test;

import static data.OutputConstractor.makeTextWithDictionary;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class OutputConstractorTest {

    @Test
    public void createReadingText() {
        String expectedResult = "London is the capital of the United Kingdom" +
                "\n" +
                "is the";

        String text = "London is the capital of the United Kingdom";
        String dictionary = "is the";

        String result = makeTextWithDictionary(text, dictionary);

        assertThat(result, is(expectedResult));
    }

}
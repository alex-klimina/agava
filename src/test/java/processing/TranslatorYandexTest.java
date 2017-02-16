package processing;

import org.junit.Test;
import users.User;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TranslatorYandexTest {

    @Test
    public void shouldCreateDictionaryForGivenWords() {
        List<String> unknownWords = Arrays.asList("capital", "chebura");
        MaterialConstructor constructor = new MaterialConstructor(User.DEFAULT_USER, new TranslatorYandex());
        LinkedHashMap<String, String> dictionary = constructor.createDictionaryWithWords(unknownWords);

        LinkedHashMap<String, String> expectedDictionary = new LinkedHashMap<>();
        expectedDictionary.put("capital", "капитал");
        expectedDictionary.put("chebura", "\"чебура\"");

        assertThat(dictionary, is(expectedDictionary));
    }

}
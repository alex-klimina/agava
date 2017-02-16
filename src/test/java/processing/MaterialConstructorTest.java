package processing;

import data.TrainingMaterial;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import users.User;
import users.Vocabulary;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static processing.MaterialConstructor.deletePunctuationMarks;
import static processing.MaterialConstructor.getUnknownWords;

public class MaterialConstructorTest {

    @Test
    public void shouldCreateTrainingMaterialForUserAndText() {
//        London is the capital of the United Kingdom.
        Set<String> knownWords = new TreeSet<>(
                Arrays.asList("Cheba is the of the Kingdom".split(" ")));
        Vocabulary vocabulary = new Vocabulary(knownWords);
        User user = new User(vocabulary);
        String sourceText = "Cheba is the capital of the Cheburashka Kingdom.";

        MaterialConstructor constructor = new MaterialConstructor(user, new Translator());
        TrainingMaterial trainingMaterial = constructor.createTextWitnNewWords(sourceText);

        LinkedHashMap<String, String> dictionary = new LinkedHashMap<>();
        dictionary.put("capital", "главный, основной, высший");
        dictionary.put("Cheburashka", "перевод не найден");
        TrainingMaterial expectedTrainingMaterial = new TrainingMaterial(sourceText, dictionary);
        Assert.assertThat(trainingMaterial, CoreMatchers.is(expectedTrainingMaterial));
    }

    @Test
    public void createSetUnknownWords() {
        String sourceText = "Cheba is the capital of the Cheburashka Kingdom.";
        Set<String> knownWords = new HashSet<>(Arrays.asList(new String[]{"Cheba", "is", "the", "of", "the", "Kingdom"}));
        Vocabulary vocabulary = new Vocabulary(knownWords);
        List<String> unknownWords = getUnknownWords(sourceText, vocabulary);

        List<String> expectedUnknownWords = Arrays.asList("capital", "Cheburashka");
        assertThat(unknownWords, is(expectedUnknownWords));
    }

    @Test
    public void shouldDeletePunctuationMarksOnEnd() {
        String word = "Kingdom.";
        assertThat(deletePunctuationMarks(word), is("Kingdom"));

        word = "capital, ";
        assertThat(deletePunctuationMarks(word), is("capital"));

        word = "options:";
        assertThat(deletePunctuationMarks(word), is("options"));

        word = " father-in-law";
        assertThat(deletePunctuationMarks(word), is("father-in-law"));
    }

    @Test
    public void shoudlReturnEmptyStringForHyphen() {
        String word = "-";
        assertThat(deletePunctuationMarks(word), is(""));

        word = "- ";
        assertThat(deletePunctuationMarks(word), is(""));

        word = " - ";
        assertThat(deletePunctuationMarks(word), is(""));
    }

    @Test
    public void shouldNotDeleteHyphen() {
        String hyphenWord = "Twenty-five";
        assertThat(deletePunctuationMarks(hyphenWord), is(hyphenWord));
    }

    @Test
    public void shouldCreateDictionaryForGivenWords() {
        List<String> unknownWords = Arrays.asList("capital", "cheburashka");
//        delete this horror
        LinkedHashMap<String, String> dictionary = new MaterialConstructor(User.DEFAULT_USER, new Translator()).createDictionaryWithWords(unknownWords);

        LinkedHashMap<String, String> expectedDictionary = new LinkedHashMap<>();
        expectedDictionary.put("capital", "главный, основной, высший");
        expectedDictionary.put("cheburashka", "перевод не найден");

        assertThat(dictionary, is(expectedDictionary));
    }

    @Test
    public void shouldCreateDataMaterialForUser() {
        Set<String> knownWords = new HashSet<>(Arrays.asList(new String[]{"I", "am", "cat"}));
        Vocabulary vocabulary = new Vocabulary(knownWords);
        User user = new User(vocabulary);
        MaterialConstructor constructor = new MaterialConstructor(user, new Translator());

        String sourceText = "I am admirable cat!";

        LinkedHashMap<String, String> dictionary = new LinkedHashMap<>();
        dictionary.put("admirable", "восхитительный");
        TrainingMaterial expectedTrainingMaterial = new TrainingMaterial(sourceText, dictionary);
        assertThat(constructor.createTextWitnNewWords(sourceText), is(expectedTrainingMaterial));
    }

}
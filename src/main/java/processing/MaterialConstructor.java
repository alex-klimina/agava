package processing;

import data.TrainingMaterial;
import lombok.AllArgsConstructor;
import users.User;
import users.Vocabulary;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class MaterialConstructor {
    private User user;

    public TrainingMaterial createTextWitnNewWords(String sourceText) {
        List<String> unknownWords = getUnknownWords(sourceText, user.getVocabulary());
        LinkedHashMap<String, String> dictionary = createDictionaryWithWords(unknownWords);

        TrainingMaterial material = new TrainingMaterial();
        material.setText(sourceText);
        material.setDictionary(dictionary);
        return material;
    }

    static LinkedHashMap<String, String> createDictionaryWithWords(List<String> unknownWords) {
        LinkedHashMap<String, String> dictionary = new LinkedHashMap<>(unknownWords.size());
        for (String word: unknownWords) {
            dictionary.put(word, findTranslation(word));
        }
        return dictionary;
    }

    private static String findTranslation(String word) {
        return Translator.translate(word);
    }

    static List<String> getUnknownWords(String sourceText, Vocabulary vocabulary) {
        return Arrays.stream(sourceText.split(" "))
                .map(MaterialConstructor::deletePunctuationMarks)
                .filter(s -> !s.isEmpty())
                .filter(s -> !vocabulary.getKnownWords().contains(s))
                .distinct()
                .collect(Collectors.toList());
    }

    static String deletePunctuationMarks(String word) {
        word = word.trim();

        if (word.equals("-")) {
            return "";
        }

        List<String> punctuationMarks = Arrays.asList(".", ",", ":", "!", "?");

        for (String mark: punctuationMarks) {
            word = word.replace(mark, "");
        }
        return word.trim();
    }
}

package data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.Arrays.asList;

public class OutputConstractor {

    @AllArgsConstructor
    static class TextWithHints {
        String txt;
        LinkedHashMap<String, String> hints = new LinkedHashMap<>();
    }

    public static String makeTextWithDictionary(String fileContent, String dictionary) {

        List<String> wordsInText = asList(fileContent.split(" "));
        List<String> wordsInDictionary = asList(dictionary.split(" "));

        List<String> unknownWords = wordsInText.stream()
                .filter(w -> wordsInDictionary.contains(w))
                .distinct()
                .collect(Collectors.toList());

        return fileContent + "\n" + unknownWords.stream().collect(Collectors.joining(" "));
    }


}

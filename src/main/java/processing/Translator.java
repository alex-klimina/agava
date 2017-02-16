package processing;


import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
    private static final LinkedHashMap<String, String> DICTIONARY = new LinkedHashMap<>();

    static  {
        URI uri;
        try {
            uri = Translator.class.getResource("top5000.txt").toURI();
            File file = new File(uri);
            List<String> allLines = Files.readAllLines(file.toPath());

            for (String line: allLines) {
                Pair<String, String> pair = createPair(line);
                DICTIONARY.put(pair.getKey(), pair.getValue());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Pair<String, String> createPair(String string) {
        Pattern pattern = Pattern.compile("[^-]*");
        Matcher matcher = pattern.matcher(string);

        String origin = "";
        String transtlation = "";
        if (matcher.find()) {
//            TODO check case not found
            origin = matcher.group();
            transtlation = string.substring(origin.length() + 1);
        }
        return new Pair<>(origin.trim(), transtlation.trim());
    }


    public static String translate(String word) {
        if (DICTIONARY.containsKey(word.toLowerCase())) {
            return DICTIONARY.get(word);
        } else {
            return "перевод не найден";
        }
    }
}

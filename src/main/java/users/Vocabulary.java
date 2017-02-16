package users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Getter
public class Vocabulary {
    Set knownWords = new TreeSet();

    private static Vocabulary DEFAULT_VOCABULARY;

    public static Vocabulary getDefaultVocabulary() {
        DEFAULT_VOCABULARY = new Vocabulary(readTheMostFrequentlyUsedWords(100));
        return DEFAULT_VOCABULARY;
    }

    private static Set<String> readTheMostFrequentlyUsedWords(int i) {
//        TODO replace to RuntimeException
        Set returnSet = Collections.emptySet();
        try {
            URI uri = Vocabulary.class.getResource("vocabulary100").toURI();
            File file = new File(uri);
            List<String> allLines = Files.readAllLines(file.toPath());
            returnSet = new TreeSet(allLines);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnSet;
    }

}

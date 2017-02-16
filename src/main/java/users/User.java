package users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private final Vocabulary vocabulary;

    public static final User DEFAULT_USER = new User(Vocabulary.getDefaultVocabulary());

}

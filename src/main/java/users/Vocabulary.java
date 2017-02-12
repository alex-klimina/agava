package users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Getter
public class Vocabulary {
    Set knownWords = new TreeSet();
}

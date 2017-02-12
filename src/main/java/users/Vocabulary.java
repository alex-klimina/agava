package users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
@AllArgsConstructor
public class Vocabulary {
    Set knownWords = new TreeSet();

}

package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TrainingMaterial {
    String text;
    LinkedHashMap<String, String> dictionary = new LinkedHashMap<>();
}

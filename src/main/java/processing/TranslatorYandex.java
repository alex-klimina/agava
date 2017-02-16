package processing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TranslatorYandex extends Translator {
    /* формат запроса
    https://translate.yandex.net/api/v1.5/tr.json/translate ?
key=<API-ключ>
 & text=<переводимый текст>
 & lang=<направление перевода>
 & [format=<формат текста>]
 & [options=<опции перевода>]
 & [callback=<имя callback-функции>]
 */

    private static final String KEY = "trnsl.1.1.20170216T151803Z.f294326e03d467a0.7a984b0b7348d86f6180155cf213b558293f78e9";

    @Override
    protected String translate(String word) {
        String translation = null;
        URL url = null;
        HttpsURLConnection connection = null;
        String stringUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate" +
                "?key=" + KEY +
                "&text=" + word +
                "&lang=en-ru";
        try {
            url = new URL(stringUrl);
            connection = (HttpsURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = connection.getInputStream();
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readValue(result, JsonNode.class); // парсинг текста
            translation = rootNode.get("text").get(0).textValue(); // получение строки из поля "message"
        } catch (IOException e) {
            e.printStackTrace();
        }

        return translation;
    }
}

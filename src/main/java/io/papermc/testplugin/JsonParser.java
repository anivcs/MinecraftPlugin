package io.papermc.testplugin;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonParser {

    public static <T> T parseStream(InputStream inputStream, Class<T> classType) {
        Gson gson = new Gson();



        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

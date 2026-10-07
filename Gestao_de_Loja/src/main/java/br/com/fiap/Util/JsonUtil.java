package br.com.fiap.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class,
            new LocalDateAdapter(FORMATTER)).create();

    public static String ConverterParaJson(Object objeto) {
        return gson.toJson(objeto);
    }

    public static <T> T ConverterDeJson(String json, Class<T> classe) {
        return gson.fromJson(json, classe);
    }

    public static <T> List<T> ConverterListaDeJson(String json, Class<T> classe) {
        Type tipoLista = TypeToken.getParameterized(List.class, classe).getType();

        return gson.fromJson(json, tipoLista);
    }
}

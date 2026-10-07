package br.com.fiap.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoUtil {
    private static final String DIRETORIO = "json";

    public static void SalvarArquivo(String nomeArquivo, String conteudo) {
        try {

            Path diretorio = Paths.get(DIRETORIO);

            if (!Files.exists(diretorio))
                Files.createDirectories(diretorio);

            Path arquivo = diretorio.resolve(nomeArquivo);

            Files.writeString(arquivo, conteudo, StandardCharsets.UTF_8);

            System.out.println("Arquivo salvo com sucesso: " + arquivo.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static String LerArquivo(String nomeArquivo) {
        try {

            Path arquivo = Paths.get(DIRETORIO, nomeArquivo);

            if (!Files.exists(arquivo))
                throw new IllegalArgumentException("Arquivo não encontrado: " + arquivo.toAbsolutePath());

            return Files.readString(arquivo, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo JSON: " + e.getMessage(), e);
        }
    }
}

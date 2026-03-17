package com.example.utez2epacientesjavafxequipo07.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class BooksFileRepository {
    private final Path filePath = Paths.get("data", "books.csv");

    private void ensureFile() throws IOException {
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    //extrae los datos del archivo en listado
    public List<String> readAllLines() throws IOException {
        ensureFile();
        return Files.readAllLines(filePath);

    }

    public void appenNewLine(String line) throws IOException {
        ensureFile();
        Files.writeString(filePath, line + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void saveFile(List <String> line) throws IOException {
        ensureFile();
        Files.write(filePath, line, StandardCharsets.UTF_8);
        StandardOpenOption truncateExisting = StandardOpenOption.TRUNCATE_EXISTING;
    }
}

package com.jss.anagram.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jasvinder S Saggu
 */

public class FileUtils {
    public static Optional<List<String>> lines(final String fileName) {
        try (Stream<String> stream = Files.lines(new File(fileName).toPath())) {
            return Optional.of(stream.collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

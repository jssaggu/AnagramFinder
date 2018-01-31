package com.jss.anagram.dao.impl;

import com.jss.anagram.dao.AnagramDataStore;
import com.jss.anagram.utils.FileUtils;

import java.util.*;

/**
 * This class is a file based implementation for {@link AnagramDataStore} interface.
 *
 * Specified words file is loaded into the memory (Map) at startup, and made it ready for queries.<br>

 * Assumption: Every row in the file is a unique word
 *
 * Map Structure:
 *  Key: Computed unique key to represent all possible anagrams (Uppercase the word and Sort it to make a unique key. e.g. tac = ACT, cat = ACT etc)
 *  Value: List of original values (e.g. {cat, tac})
 *
 * @author Jasvinder S Saggu
 */
public class AnagramDataStoreFileImpl implements AnagramDataStore {

    private final Map<String, List<String>> anagramDataset;

    public AnagramDataStoreFileImpl(final String datafileName) {
        anagramDataset = loadAndPopulateAnagramDatasetFrom(datafileName);
    }

    private Map<String, List<String>> loadAndPopulateAnagramDatasetFrom(final String fileName) {
        final List<String> rows = FileUtils.lines(fileName).orElse(Collections.emptyList());

        final Map<String, List<String>> map = new HashMap<>();

        for (final String row : rows) {
            final String sortedWord = uniqueKeyOf(row);
            if (map.containsKey(sortedWord)) {
                map.get(sortedWord).add(row);
            } else {
                final List<String> list = new ArrayList<>();
                list.add(row);
                map.put(sortedWord, list);
            }
        }
        return Collections.unmodifiableMap(map);
    }

    private static String uniqueKeyOf(final String word) {
        final char[] sortedWord = word.toUpperCase().toCharArray();
        Arrays.sort(sortedWord);
        return new String(sortedWord);
    }

    @Override
    public Optional<List<String>> getMatchingAnagramsFor(final String word) {
        return Optional.ofNullable(anagramDataset.get(uniqueKeyOf(word)));
    }
}
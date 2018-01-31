package com.jss.anagram.dao;

import java.util.*;

/**
 * This Data Access Object class supports single word or multiple word search
 *
 * @author Jasvinder S Saggu
 */
public class AnagramDAO {

    private final AnagramDataStore store;

    public AnagramDAO(AnagramDataStore store) {
        this.store = store;
    }

    public Optional<List<String>> findAnagramsForSingle(final String word) {
        return store.getMatchingAnagramsFor(word);
    }

    /**
     * Iterate through all the words and collect them in a list. The list itself contains a Map.
     * The key of the Map is representing the original word and list is a list of possible anagram matches.
     *
     */
    public List<Map<String, List<String>>> findAnagramsForAll(final List<String> words) {
        final List<Map<String, List<String>>> matchingList = new ArrayList<>();
        for (String word : words) {
            final Map<String, List<String>> matchingWordsMap = new HashMap<>();
            matchingWordsMap.put(word, findAnagramsForSingle(word).orElse(Collections.emptyList()));
            matchingList.add(matchingWordsMap);
        }
        return matchingList;
    }
}

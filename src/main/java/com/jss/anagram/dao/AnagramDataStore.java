package com.jss.anagram.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author Jasvinder S Saggu
 * @date 31/01/2018
 */
public interface AnagramDataStore {

    Optional<List<String>> getMatchingAnagramsFor(String word);
}

package com.jss.anagram;

import com.jss.anagram.dao.AnagramDataStore;
import com.jss.anagram.dao.impl.AnagramDataStoreFileImpl;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AnagramDataStoreTest extends TestCase {

    private AnagramDataStore data;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        data = new AnagramDataStoreFileImpl("src/test/resources/wordlist.txt");
    }

    public void testAppleReturnsMultipleMatches() {
        final List<String> list = data.getMatchingAnagramsFor("apple").get();
        assertTrue(list.size() == 3);
        assertTrue(list.containsAll(Arrays.asList("appel", "Apple", "pepla")));
    }

    public void testAppleReturnsMultipleMatchesIgnoringCases() {
        final List<String> list = data.getMatchingAnagramsFor("AppLe").get();
        assertTrue(list.size() == 3);
        assertTrue(list.containsAll(Arrays.asList("appel", "Apple", "pepla")));
    }

    public void testNonAalphanumericCharactersAreConsidered() {
        final List<String> list = data.getMatchingAnagramsFor("he's").get();
        assertTrue(list.size() == 2);
        assertTrue(list.containsAll(Arrays.asList("she'", "he's")));
        assertTrue(!list.contains("she"));
    }

    public void testNoMatchForFoo() {
        final Optional<List<String>> list = data.getMatchingAnagramsFor("Foo");
        assertFalse(list.isPresent());
    }
}
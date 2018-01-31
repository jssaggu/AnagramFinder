package com.jss.anagram.service;

import com.jss.anagram.dao.AnagramDAO;
import com.jss.anagram.dao.impl.AnagramDataStoreFileImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * REST service to find anagrams
 *
 * @author Jasvinder S Saggu
 */
@RestController
public class AnagramController {

    private AnagramDAO dao = new AnagramDAO(new AnagramDataStoreFileImpl("src/main/resources/wordlist.txt"));

    @RequestMapping(value = "/{anagramWord}", method = RequestMethod.GET)
    public List<Map<String, List<String>>> findAnagramsFor(@PathVariable("anagramWord") final String anagramWord) {
        //User can specify a list of words separated by commas (e.g. apple,cat,bat), so split them in to words
        return dao.findAnagramsForAll(Arrays.asList(anagramWord.split(",")));
    }
}
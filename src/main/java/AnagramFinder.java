import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Anagram Code Kata
 * - find all anagrams and print them one per row
 * - find the longest word that is an anagram
 * - find the anagram with the most words
 */
public class AnagramFinder {
    private static final String FILE_PATH = "/home/anders/code/kata/src/main/resources/words.txt";

    public Collection<Collection<String>> findAnagrams(List<String> words) {
        Multimap<String, String> map = LinkedHashMultimap.create();
        for (String word : words) {
            String key = createKey(word);
            map.put(key, word);
        }
        removeNonAnagrams(map);
        return map.asMap().values();
    }

    private void removeNonAnagrams(Multimap<String, String> map) {
        final Iterator<Map.Entry<String, Collection<String>>> iterator = map.asMap().entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Collection<String>> anagram = iterator.next();
            if (anagram.getValue().size() < 2) {
                iterator.remove();
            }
        }
    }

    private String createKey(String word) {
        final char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public Collection<String> findLongestAnagrams(List<String> words) {
        final Collection<Collection<String>> anagrams = findAnagrams(words);
        int maxLength = 0;
        Collection<String> maxCollection = null;

        for (Collection<String> anagram : anagrams) {
            final int length = anagram.iterator().next().length();
            if (length > maxLength) {
                maxLength = length;
                maxCollection = anagram;
            }
        }
        return maxCollection;
    }
}

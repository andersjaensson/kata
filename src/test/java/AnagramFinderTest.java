import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class AnagramFinderTest {
    @Test
    public void findsAnagrams() throws Exception {
        List<String> words = Arrays.asList("sirap", "paris", "banan");
        Collection<Collection<String>> anagrams = new AnagramFinder().findAnagrams(words);
        assertThat(anagrams, hasItem((Collection<String>) Sets.newHashSet("sirap", "paris")));
        assertThat(anagrams, hasItem((Collection<String>) Sets.newHashSet("banan")));
        assertThat(anagrams.size(), is(2));
    }

    @Test
    public void findsAnagramsFromFile() throws Exception {
        final List<String> words = readFromFile();
        final Collection<Collection<String>> anagrams = new AnagramFinder().findAnagrams(words);
        assertThat(anagrams.size(), is(20683));
    }

    private List<String> readFromFile() throws IOException {
        File wordsFile = new File("/home/anders/code/kata/src/main/resources/words.txt");
        return Files.readAllLines(wordsFile.toPath(), StandardCharsets.ISO_8859_1);
    }


    @Test
    public void findsLongestWord() {
        List<String> words = Arrays.asList("abba", "baba", "sirap", "paris");
        Collection<String> anagrams = new AnagramFinder().findLongestAnagrams(words);
        assertThat(anagrams, hasItems("sirap", "paris"));
    }

    @Test
    public void findsLongestWordInFile() throws IOException {
        final List<String> words = readFromFile();
        Collection<String> anagrams = new AnagramFinder().findLongestAnagrams(words);
        assertThat(anagrams, hasItems("acoustoelectrically", "electroacoustically"));
    }
}

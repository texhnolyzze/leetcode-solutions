import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Stream<String> words = Pattern.compile("\\p{Punct}+|\\p{Space}+").
            splitAsStream(paragraph).parallel().filter(
                 s -> !s.isEmpty() && !bannedSet.contains(s.toLowerCase())
        );
        return words.collect(groupingBy(String::toLowerCase, counting())).entrySet().stream().max(
            (e1, e2) -> Long.compare(e1.getValue(), e2.getValue())
        ).get().getKey();
    }
}
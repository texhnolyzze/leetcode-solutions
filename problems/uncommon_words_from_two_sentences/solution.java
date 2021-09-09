class Solution {
    public String[] uncommonFromSentences(
        final String s1,
        final String s2
    ) {
        final List<String> res = new LinkedList<>();
        final Map<String, Long> s1Words = Arrays.stream(s1.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final Map<String, Long> s2Words = Arrays.stream(s2.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        s1Words.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).filter(Predicate.not(s2Words::containsKey)).forEach(res::add);
        s2Words.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).filter(Predicate.not(s1Words::containsKey)).forEach(res::add);
        return res.toArray(String[]::new);
    }
}
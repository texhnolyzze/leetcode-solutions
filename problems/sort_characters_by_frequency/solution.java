class Solution {
    public static String frequencySort(String s) {
        final char[] countOf = new char[128];
        for (int i = 0; i < s.length(); i++) 
            countOf[s.charAt(i)]++;
        StringBuilder sb = new StringBuilder(s.length());
        Integer[] indices = new Integer[128];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> -Integer.compare(countOf[i1], countOf[i2]));
        for (int i : indices) {
            int count = countOf[i];
            char c = (char) i;
            for (int j = 0; j < count; j++)
                sb.append(c);
        }
        return sb.toString();
    }
}
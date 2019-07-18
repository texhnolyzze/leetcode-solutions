public class Codec {
        
        private Map<String, String> map = new HashMap<>();
        
//      Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            while (true) {
                String id = generateId();
                if (!map.containsKey(id)) {
                    map.put(id, longUrl);
                    return id;
                }
            }
        }

//      Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
        
        private Random rand = new Random(System.nanoTime());
        private char[] alphabet = new char[10 + 2 * 26];
        {
            for (int i = 0; i < 26; i++) alphabet[i] = (char) ('a' + i);
            for (int i = 0; i < 26; i++) alphabet[i + 26] = (char) ('A' + i);
            for (int i = 0; i < 10; i++) alphabet[i + 2 * 26] = (char) ('0' + i);
        }
        private int idSize = 6;
        private String generateId() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idSize; i++)
                sb.append(alphabet[rand.nextInt(alphabet.length)]);
            return sb.toString();
        }
        
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
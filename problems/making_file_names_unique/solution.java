class Solution {
    public String[] getFolderNames(final String[] names) {
        final Map<String, Integer> table = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            final String name = names[i];
            int serial = table.getOrDefault(name, -1);
            if (serial == -1) {
                table.put(name, 1);
            } else {
                String temp;
                while (true) {
                    temp = name + '(' + serial + ')';
                    if (table.containsKey(temp)) {
                        serial++;
                    } else {
                        table.put(temp, 1);
                        break;
                    }
                }
                table.put(name, serial + 1);
                names[i] = temp;
            }
        }
        return names;
    }
}
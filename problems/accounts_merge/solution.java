class Solution {
    public List<List<String>> accountsMerge(final List<List<String>> accounts) {
        final Map<String, List<Integer>> emailToPersons = new HashMap<>(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            final List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                final String email = account.get(j);
                emailToPersons.computeIfAbsent(email, unused -> new ArrayList<>(1)).add(i);
            }
        }
        final Map<Integer, Set<String>> personToEmails = new HashMap<>(accounts.size());
        final BitSet seen = new BitSet(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            if (seen.get(i)) {
                continue;
            }
            seen.set(i);
            final Set<String> emails = new TreeSet<>();
            collectEmails(i, accounts, emailToPersons, emails, seen);
            personToEmails.put(i, emails);
        }
        final List<List<String>> res = new ArrayList<>(personToEmails.size());
        for (final Map.Entry<Integer, Set<String>> entry : personToEmails.entrySet()) {
            final int representative = entry.getKey();
            final String name = accounts.get(representative).get(0);
            final List<String> mergedAccount = new ArrayList<>(entry.getValue().size());
            mergedAccount.add(name);
            mergedAccount.addAll(entry.getValue());
            res.add(mergedAccount);
        }
        return res;
    }

    private void collectEmails(
        final int personId,
        final List<List<String>> accounts,
        final Map<String, List<Integer>> emailToPersons,
        final Set<String> target,
        final BitSet seen
    ) {
        final List<String> account = accounts.get(personId);
        for (int i = 1; i < account.size(); i++) {
            final String email = account.get(i);
            target.add(email);
            final List<Integer> persons = emailToPersons.get(email);
            for (int j = 0; j < persons.size(); j++) {
                final int otherPersonId = persons.get(j);
                if (!seen.get(otherPersonId)) {
                    seen.set(otherPersonId);
                    collectEmails(otherPersonId, accounts, emailToPersons, target, seen);
                }
            }
        }
    }
}
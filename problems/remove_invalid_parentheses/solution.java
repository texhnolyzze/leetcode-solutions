class Solution {
    private int minDeletions;
    private Stack stack;
    private List<String> validParentheses;

    public List<String> removeInvalidParentheses(final String s) {
        minDeletions = Integer.MAX_VALUE;
        stack = new Stack(s.length());
        validParentheses = new LinkedList<>();
        removeInvalidParentheses(
            new StringBuilder(s),
            0,
            ((int) s.chars().filter(c -> c == '(').count()),
            ((int) s.chars().filter(c -> c == ')').count()),
            0
        );
        return validParentheses;
    }

    private void removeInvalidParentheses(
        final StringBuilder builder,
        final int pos,
        final int numOpenBrackets,
        final int numClosingBrackets,
        final int deletions
    ) {
        if (deletions + Math.abs(numOpenBrackets - numClosingBrackets) > minDeletions || definitelyInvalid(builder, pos)) {
            return;
        }
        if (deletions == minDeletions) {
            if (valid(builder)) {
                final String valid = builder.toString();
                if (!validParentheses.contains(valid)) {
                    validParentheses.add(valid);
                }
            }
        } else if (deletions < minDeletions) {
            if (valid(builder)) {
                minDeletions = deletions;
                validParentheses.clear();
                validParentheses.add(builder.toString());
            } else {
                if (pos < builder.length()) {
                    final char c = builder.charAt(pos);
                    if (c != '(' && c != ')') {
                        removeInvalidParentheses(builder, pos + 1, numOpenBrackets, numClosingBrackets, deletions);
                    } else {
                        removeInvalidParentheses(builder, pos + 1, numOpenBrackets, numClosingBrackets, deletions);
                        builder.deleteCharAt(pos);
                        if (c == '(') {
                            removeInvalidParentheses(builder, pos, numOpenBrackets - 1, numClosingBrackets, deletions + 1);
                        } else {
                            removeInvalidParentheses(builder, pos, numOpenBrackets, numClosingBrackets - 1, deletions + 1);
                        }
                        builder.insert(pos, c);
                    }
                }
            }
        }
    }
    
    private boolean definitelyInvalid(
        final StringBuilder s,
        final int to
    ) {
        stack.clear();
        for (int i = 0; i < to; i++) {
            final char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (
                c == ')' &&
                (
                    stack.empty() ||
                    stack.pop() != '('
                )
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean valid(final StringBuilder s) {
        stack.clear();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (
                c == ')' &&
                (
                    stack.empty() ||
                    stack.pop() != '('
                )
            ) {
                return false;
            }
        }
        return stack.empty();
    }

    private static class Stack {

        private final char[] stack;
        private int pos;

        private Stack(final int n) {
            stack = new char[n];
        }

        private void clear() {
            pos = 0;
        }

        private void push(final char c) {
            stack[pos++] = c;
        }

        private char peek() {
            return stack[pos - 1];
        }

        private char pop() {
            return stack[--pos];
        }

        private boolean empty() {
            return pos == 0;
        }

    }
}
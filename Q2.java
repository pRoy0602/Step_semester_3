public class Q2 {
    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0, firstMismatch = -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) matched++;
            else if (firstMismatch == -1) firstMismatch = i;
        }
        double accuracy = original.length() == 0 ? 100.0 :
                matched * 100.0 / original.length();
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ",
                matched, original.length(), accuracy);
        if (firstMismatch == -1)
            System.out.println("No Mismatches");
        else
            System.out.println("First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '"
                    + typed.charAt(firstMismatch) + "')");
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}

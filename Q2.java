public class Q2 {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(' ');
            result.append(new StringBuilder(words[i]).reverse());
        }
        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }
}

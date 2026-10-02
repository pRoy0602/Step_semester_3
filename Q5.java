import java.util.*;

public class Q5 {
    static void printFilteredWordFrequency(String feedback) {
        Set<String> stop = new HashSet<>(Arrays.asList("the","was","and","a","is","of","in"));
        Map<String,Integer> freq = new HashMap<>();
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        for (String word : cleaned.trim().split("\\s+")) {
            if (!stop.contains(word))
                freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((x,y) -> Integer.compare(y.getValue(), x.getValue()));
        for (Map.Entry<String,Integer> e : list)
            System.out.println(e.getKey() + ": " + e.getValue());
    }

    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}

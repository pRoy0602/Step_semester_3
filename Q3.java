public class Q3 {
    static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) return;
        char bestColor = signalLog.charAt(0), current = signalLog.charAt(0);
        int best = 1, currentCount = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == current) currentCount++;
            else {
                current = signalLog.charAt(i);
                currentCount = 1;
            }
            if (currentCount > best) {
                best = currentCount;
                bestColor = current;
            }
        }
        System.out.println("Longest Streak: '" + bestColor + "' repeated " + best + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}

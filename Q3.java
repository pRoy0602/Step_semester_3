import java.util.HashMap;
import java.util.Map;

public class Q3 {

    // Counts contiguous subarrays whose sum is exactly k.
    public static long countPeriods(int[] transactions, long k) {
        Map<Long, Long> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0L, 1L);

        long runningSum = 0;
        long count = 0;

        for (int value : transactions) {
            runningSum += value;

            // If an earlier prefix was runningSum - k, the intervening period sums to k.
            count += prefixFrequency.getOrDefault(runningSum - k, 0L);
            prefixFrequency.put(
                runningSum,
                prefixFrequency.getOrDefault(runningSum, 0L) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println(countPeriods(transactions, 7));
        // Expected: 4

        int[] transactions2 = {1, 2, 3};
        System.out.println(countPeriods(transactions2, 10));
        // Expected: 0
    }
}

/*
Complexity:
- Brute force that separately sums each subarray: O(n^3).
- Optimized prefix sum + hash map: O(n) expected time and O(n) additional space.
Long is used for sums and counts to avoid integer overflow.
*/

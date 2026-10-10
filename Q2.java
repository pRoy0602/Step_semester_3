import java.util.Arrays;

public class Q2 {

    // Returns {maximum length, earliest start index}; returns {0, -1} if none fits.
    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long windowSum = 0;
        int bestLength = 0;
        int bestStart = -1;

        for (int right = 0; right < costs.length; right++) {
            windowSum += costs[right];

            // Non-negative costs mean shrinking the left side cannot increase the sum.
            while (left <= right && windowSum > budget) {
                windowSum -= costs[left];
                left++;
            }

            int length = right - left + 1;
            // Strictly greater preserves the earliest start in a tie.
            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }

        return new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        System.out.println(Arrays.toString(longestStreak(costs, 8)));
        // Expected: [4, 4]

        int[] costs2 = {9, 10};
        System.out.println(Arrays.toString(longestStreak(costs2, 8)));
        // Expected: [0, -1]
    }
}

/*
Complexity:
- Checking every possible subarray by summing each one: O(n^3) in a direct approach.
- Optimized sliding window: O(n) time and O(1) additional space.
Non-negative values are essential: extending a window never lowers its sum, and
removing values from the left never increases it. Negative values break this property.
*/

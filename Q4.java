public class Q4 {

    // Counts scores in the inclusive range [low, high] in a sorted array.
    public static int countInBand(int[] scores, int low, int high) {
        int firstAtLeastLow = lowerBound(scores, low);
        int firstGreaterThanHigh = upperBound(scores, high);
        return firstGreaterThanHigh - firstAtLeastLow;
    }

    // First index i for which scores[i] >= target; returns length if none exists.
    private static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // First index i for which scores[i] > target; returns length if none exists.
    private static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));  // Expected: 6
        System.out.println(countInBand(scores, 90, 100)); // Expected: 0
    }
}

/*
Complexity:
- Linear scan: O(n) time and O(1) additional space.
- Two binary searches: O(log n) time and O(1) additional space.
Finding the first score >= low and first score > high correctly includes duplicates
at both edges of the requested band.
*/

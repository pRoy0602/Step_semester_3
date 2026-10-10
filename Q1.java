import java.util.Arrays;

public class Q1 {

    // Returns the sum for each inclusive [start, end] query using prefix sums.
    public static long[] footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        long[] prefix = new long[n + 1];

        // prefix[i] stores the sum of visitors[0] through visitors[i - 1].
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        long[] result = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            result[i] = prefix[end + 1] - prefix[start];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};

        System.out.println(Arrays.toString(footfallReport(visitors, queries)));
        // Expected: [22, 31, 27, 9]
    }
}

/*
Complexity:
- Naive: O(n * q) in the worst case, because each query may scan the whole range.
- Optimized: O(n + q) time, including O(n) prefix-sum preprocessing.
- Additional space: O(n + q) for the prefix array and result array.
Preprocessing is worthwhile when many queries reuse the same visitor array.
*/

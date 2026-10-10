import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q5 {

    // Returns grid values in clockwise spiral order, including rectangular grids.
    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse the top row from left to right.
            for (int col = left; col <= right; col++) {
                result.add(grid[top][col]);
            }
            top++;

            // Traverse the right column from top to bottom.
            for (int row = top; row <= bottom; row++) {
                result.add(grid[row][right]);
            }
            right--;

            // Only traverse the bottom row if one still remains.
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(grid[bottom][col]);
                }
                bottom--;
            }

            // Only traverse the left column if one still remains.
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(grid[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(auditRoute(grid));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}

/*
Complexity:
- Time: O(m * n), because each grid cell is visited exactly once.
- Additional space excluding the output list: O(1).
*/

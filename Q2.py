def warehouseSummary(grid):
    if not grid or not grid[0]:
        return (0, (-1, -1))
    total = 0
    max_value = grid[0][0]
    max_row = max_col = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            total += grid[i][j]
            if grid[i][j] > max_value:
                max_value = grid[i][j]
                max_row, max_col = i, j
    return (total, (max_row, max_col))

grid = [[4, 9, 2], [7, 1, 6], [3, 12, 5]]
print(warehouseSummary(grid))
# Time: O(m*n), Space: O(1)

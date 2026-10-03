def maxSumSubarray(sales, k):
    if k < 1 or k > len(sales):
        raise ValueError("k must satisfy 1 <= k <= len(sales)")
    window = sum(sales[:k])
    maximum = window
    for i in range(k, len(sales)):
        window += sales[i] - sales[i-k]
        maximum = max(maximum, window)
    return maximum

print(maxSumSubarray([2, 1, 5, 1, 3, 2], 3))
# Naive: O(n*k). Optimized sliding window: O(n) time, O(1) space.

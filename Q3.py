def findBook(catalog, targetIsbn):
    low, high = 0, len(catalog) - 1
    while low <= high:
        mid = (low + high) // 2
        if catalog[mid][0] == targetIsbn:
            return catalog[mid][1]
        if catalog[mid][0] < targetIsbn:
            low = mid + 1
        else:
            high = mid - 1
    return "Not Found"

catalog = [
    ("0001112223", "Introduction to Algebra"),
    ("0002223334", "Beginning Python"),
    ("0003334445", "Classic Mythology"),
    ("0004445556", "Data and Society"),
    ("0005556667", "European History")
]
print(findBook(catalog, "0003334445"))
print(findBook(catalog, "0009998887"))
# Time: O(log n), Space: O(1)

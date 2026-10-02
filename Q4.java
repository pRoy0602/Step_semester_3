public class Q4 {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int a = 0, b = 0, max = Integer.MIN_VALUE;
        String section = "";
        int index = -1;
        for (int i = 0; i < sectionA.length; i++) {
            a += sectionA[i];
            if (sectionA[i] > max) { max = sectionA[i]; section = "Section A"; index = i; }
        }
        for (int i = 0; i < sectionB.length; i++) {
            b += sectionB[i];
            if (sectionB[i] > max) { max = sectionB[i]; section = "Section B"; index = i; }
        }
        System.out.println("Section A Total: " + a + " | Section B Total: " + b
                + " | Status: " + (a == b ? "Balanced" : "Not Balanced")
                + " | Highest Quantity: " + max + " (" + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {
        analyzeInventory(new int[]{20,15,30}, new int[]{25,10,30});
    }
}

public class Q4 {
    static String normalizeCode(String raw) {
        if (raw == null) return null;
        String s = raw.trim();
        if (s.length() < 3) return s;
        return s.substring(0, 3).toUpperCase() + s.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code == null || code.length() != 13)
            return "Invalid: wrong length";
        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";
        for (int i = 3; i < 13; i++)
            if (!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain only digits";
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(code.substring(0,3)).append("] YEAR: ")
          .append(code.substring(3,7)).append(" | CATALOG: ")
          .append(code.substring(7));
        return sb.toString();
    }

    public static void main(String[] args) {
        String c1 = normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(c1));
        System.out.println(validateAndFormat(normalizeCode("12N2026004251")));
    }
}

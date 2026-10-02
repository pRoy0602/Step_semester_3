public class Q4 {
    // Broken design: all three fields are shared by every object, so the second
    // construction overwrites the first object's data.
    static class BrokenLibraryMember {
        static String name, memberId;
        static int booksIssued;
        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name; BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {
        private String name, memberId;
        private int booksIssued;
        private static String libraryName = "Central Library";
        private static int memberCount = 0;

        LibraryMember(String name) {
            this.name = name;
            this.memberId = "LM-" + (1001 + memberCount);
            memberCount++;
        }
        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }
        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        BrokenLibraryMember a = new BrokenLibraryMember("Aditi","LM-X",0);
        BrokenLibraryMember b = new BrokenLibraryMember("Rohan","LM-Y",0);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        LibraryMember x = new LibraryMember("Aditi");
        LibraryMember y = new LibraryMember("Rohan");
        x.printMemberCard(); y.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}

import java.util.Arrays;

public class Q5 {
    static class LoanReceipt {
        private static int createdCount;
        static { createdCount=0; }
        private final String memberId;
        private final String[] bookIds;
        LoanReceipt(String memberId,String[] bookIds){
            if(bookIds==null)throw new IllegalArgumentException("bookIds required");
            for(String id:bookIds)if(!validBookId(id))throw new IllegalArgumentException("Invalid book ID");
            this.memberId=memberId;this.bookIds=bookIds.clone();createdCount++;
        }
        private static boolean validBookId(String id){
            if(id==null||id.length()!=6||!id.startsWith("BK-"))return false;
            return Character.isDigit(id.charAt(3))&&Character.isDigit(id.charAt(4))&&Character.isDigit(id.charAt(5));
        }
        String[] getBookIds(){return bookIds.clone();}
        LoanReceipt withCorrectedBookId(int index,String newId){
            if(index<0||index>=bookIds.length||!validBookId(newId))throw new IllegalArgumentException("Invalid correction");
            String[] copy=bookIds.clone();copy[index]=newId;
            return new LoanReceipt(memberId,copy);
        }
    }
    static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;
        ReferenceOnlyLoanReceipt(String memberId,String[] bookIds,String roomNumber){
            super(memberId,bookIds);this.roomNumber=roomNumber;
        }
    }
    static String processNightlyCirculation(LoanReceipt[] receipts){
        int processed=0,nulls=0,reference=0,regular=0;
        for(LoanReceipt r:receipts){
            if(r==null){nulls++;continue;}
            processed++;
            if(r instanceof ReferenceOnlyLoanReceipt)reference++; else regular++;
        }
        return processed+" processed | "+nulls+" null skipped | "+reference+" reference-only | "+regular+" regular";
    }
    public static void main(String[] args){
        System.out.println(processNightlyCirculation(new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001",new String[]{"BK-200"},"Reading Room 3"),
            null,new LoanReceipt("LIB-002",new String[]{"BK-201"})}));
        LoanReceipt r=new LoanReceipt("LIB-8841",new String[]{"BK-100","BK-101"});
        String[] ids=r.getBookIds();ids[0]="HACKED";
        System.out.println(Arrays.toString(r.getBookIds()));
    }
}

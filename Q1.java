public class Q1 {
    static String classifyAccess(String modifier,String context){
        if(modifier.equals("private")) return context.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        if(modifier.equals("default")) return context.equals("SAME_CLASS")||context.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
        if(modifier.equals("protected")) return context.equals("SAME_CLASS")||context.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
        if(modifier.equals("public")) return "ALLOWED";
        return "DENIED";
    }
    static String summarizeByModifier(String[][] attempts){
        String[] mods={"private","default","protected","public"};
        int[][] c=new int[4][2];
        for(String[] a:attempts){
            int idx=-1; for(int i=0;i<mods.length;i++)if(mods[i].equals(a[0]))idx=i;
            if(idx>=0)c[idx][classifyAccess(a[0],a[1]).equals("ALLOWED")?0:1]++;
        }
        StringBuilder s=new StringBuilder();
        for(int i=0;i<mods.length;i++){
            if(i>0)s.append(" | ");
            s.append(mods[i]).append(": ").append(c[i][0]).append(" allowed / ")
             .append(c[i][1]).append(" denied");
        }
        return s.toString();
    }
    static class LibraryMember {
        private String membershipId;
        private String branchCode;
        private double finesOwed;
        private String displayName;
        LibraryMember(String membershipId,String branchCode,double finesOwed,String displayName){
            if(membershipId==null||membershipId.trim().length()<4)
                throw new IllegalArgumentException("Invalid membershipId");
            this.membershipId=membershipId.trim();this.branchCode=branchCode;
            this.finesOwed=finesOwed;this.displayName=displayName;
        }
    }
    public static void main(String[] args){
        System.out.println(classifyAccess("private","SAME_CLASS"));
        System.out.println(classifyAccess("protected","DIFFERENT_PACKAGE"));
        System.out.println(summarizeByModifier(new String[][]{
            {"private","SAME_CLASS"},{"private","SAME_PACKAGE"},{"default","SAME_PACKAGE"},
            {"default","DIFFERENT_PACKAGE"},{"protected","SAME_PACKAGE"},{"protected","SAME_CLASS"},
            {"public","DIFFERENT_PACKAGE"}}));
    }
}

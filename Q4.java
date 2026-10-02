public class Q4 {
    static class LibraryMember {
        private String membershipId, name, securityAnswerHash;
        private boolean premiumMember;
        public LibraryMember(){this(null,null);}
        public LibraryMember(String name){this(null,name);}
        public LibraryMember(String membershipId,String name){
            this.membershipId=membershipId;this.name=name;
        }
        public String getMembershipId(){return membershipId;}
        public void setMembershipId(String id){if(membershipId==null)membershipId=id;}
        public String getName(){return name;}
        public void setName(String name){this.name=name;}
        public boolean isPremiumMember(){return premiumMember;}
        public void setPremiumMember(boolean premium){premiumMember=premium;}
        public void setSecurityAnswer(String answer){
            securityAnswerHash = answer==null ? null : Integer.toHexString(answer.hashCode());
        }
        // No getter for securityAnswerHash: write-only from the public API.
    }
    public static void main(String[] args){
        System.out.println(new LibraryMember("Priya Nair").getMembershipId());
        System.out.println(new LibraryMember("LIB-8841","Priya Nair").getMembershipId());
        LibraryMember m=new LibraryMember();
        m.setMembershipId("LIB-8841");m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
    }
}

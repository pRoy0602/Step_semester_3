public class Q2 {
    interface Exportable { String exportData(); }
    static class ExportCounter { static int total; }
    static class ReportGenerator implements Exportable {
        private final String reportName;
        ReportGenerator(String reportName){if(reportName==null||reportName.trim().isEmpty())throw new IllegalArgumentException();this.reportName=reportName;}
        public String exportData(){ExportCounter.total++;return "Exported report: "+reportName;}
    }
    static class UserProfile implements Exportable {
        private final String username;
        UserProfile(String username){if(username==null||username.trim().isEmpty())throw new IllegalArgumentException();this.username=username;}
        public String exportData(){ExportCounter.total++;return "Exported profile: "+username;}
    }
    static int getTotalExports(){return ExportCounter.total;}
    static void exportAll(Exportable[] items){for(Exportable x:items)System.out.println(x.exportData());}
    public static void main(String[] args){
        ReportGenerator r=new ReportGenerator("Sales Q1");
        UserProfile u=new UserProfile("jane_doe");
        exportAll(new Exportable[]{r,u});
        System.out.println(getTotalExports());
    }
}

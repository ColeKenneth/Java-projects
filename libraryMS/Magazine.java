package libraryMS;

public class Magazine extends Book {
    private int issueNumber;
    private String month;

    public Magazine(String title, String ISBN, int issueNumber, String month) {
        super(title, ISBN);
        this.issueNumber = issueNumber;
        this.month = month;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return super.toString() + "\nIssue Number: " + issueNumber + "\nMonth: " + month;
    }
    
}

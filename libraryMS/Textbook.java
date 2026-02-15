package libraryMS;

public class Textbook extends Book {
    private String subject;
    private int edition;

    public Textbook(String title, String ISBN, String subject, int edition) {
        super(title, ISBN);
        this.subject = subject;
        this.edition = edition;
    }   

    public String getSubject() {
        return subject;
    }

    public int getEdition() {
        return edition;
    }
}

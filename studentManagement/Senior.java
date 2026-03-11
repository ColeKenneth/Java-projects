package studentManagement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Senior extends Student {
    private String thesisTitle;
    private LocalDate expectedGraduationDate;
    private boolean careerReady;

    public Senior(int studentID, String firstName, String middleName, String lastName, int age, YearLevel yearLevel, String thesisTitle, LocalDate expectedGraduationDate, boolean careerReady) {
        super(studentID, firstName, middleName, lastName, age, yearLevel);
        setThesisTitle(thesisTitle);
        setGraduationDate(expectedGraduationDate);
        setCareerReady(careerReady);
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        if (thesisTitle == null) {
            throw new IllegalArgumentException("A null title is prohibited.");
        }
        if (thesisTitle.isBlank()) {
            throw new IllegalArgumentException("A title cannot be left blank.");
        }

        this.thesisTitle = thesisTitle;
    }

    public LocalDate getExpectedGraduationDate() {
        return expectedGraduationDate;
    }

    public void setGraduationDate(LocalDate expectedGraduationDate) {
       if (expectedGraduationDate == null) {
           throw new IllegalArgumentException("Cannot be a null date.");
       }

       if (expectedGraduationDate.isBefore(LocalDate.now())) {
           throw new IllegalArgumentException("Graduation can't happen in the past.");
       }

       LocalDate graduationPeriod = LocalDate.now().plusYears(2);
       if (expectedGraduationDate.isAfter(graduationPeriod)) {
           throw new GraduationOutOfRangeException("Graduation out of range!");
       }

       this.expectedGraduationDate = expectedGraduationDate;
    }

    public boolean isCareerReady() {
        return careerReady;
    }

    public void setCareerReady(boolean careerReady) {
        this.careerReady = careerReady;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nThesis Title: ").append(getThesisTitle());
        sb.append("\nExpected Graduation Date: ").append(getExpectedGraduationDate().format(DateTimeFormatter.ofPattern("MMM d, yyyy")));
        sb.append("\nIs career ready? ").append(isCareerReady() ? "Yes" : "No");

        return sb.toString();
    }

}

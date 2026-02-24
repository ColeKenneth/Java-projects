package studentManagement;

public class Freshman extends Student {
    private boolean orientationComplete;
    private String highSchoolOrigin;
    private double entranceExamScore;

    public Freshman(int studentID, String firstName, String middleName, String lastName, int age, YearLevel yearLevel, boolean orientationComplete, String highSchoolOrigin, double entranceExamScore) {
        super(studentID, firstName, middleName, lastName, age, yearLevel);
        setOrientationComplete(orientationComplete);
        setHighSchoolOrigin(highSchoolOrigin);
        setEntranceExamScore(entranceExamScore);
    }

    public boolean isOrientationComplete() {
        return orientationComplete;
    }

    public void setOrientationComplete(boolean orientationComplete) {
        this.orientationComplete = orientationComplete;
    }

    public String getHighSchoolOrigin() {
        return highSchoolOrigin;
    }

    public void setHighSchoolOrigin(String highSchoolOrigin) {
        if (highSchoolOrigin == null) {
            throw new IllegalArgumentException("Cannot be a null value.");
        }
        if (highSchoolOrigin.isBlank()) {
            throw new IllegalArgumentException("This field is required.");
        }
        this.highSchoolOrigin = highSchoolOrigin.trim();
    }

    public double getEntranceExamScore() {
        return entranceExamScore;
    }

    public void setEntranceExamScore(double entranceExamScore) {
        if (entranceExamScore < 75) {
            throw new IllegalArgumentException("Student failed to pass the 75 mark threshold.");
        }
        this.entranceExamScore = entranceExamScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n").append("Is orientation complete? ").append(isOrientationComplete() ? "Completed" : "Pending");
        sb.append("\n").append("High School Origin: ").append(getHighSchoolOrigin());
        sb.append("\n").append("Entrance Exam Score: ").append(getEntranceExamScore());

        return sb.toString();
    }

}

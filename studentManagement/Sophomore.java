package studentManagement;

public class Sophomore extends Student {
    private String declaredMajor;
    private int generalEducationCredits;
    private boolean eligibleForMinor;

    public Sophomore(int studentID, String firstName, String middleName, String lastName, int age, YearLevel yearLevel, String declaredMajor, int generalEducationCredits, boolean eligibleForMinor) {
        super(studentID, firstName, middleName, lastName, age, yearLevel);
        setDeclaredMajor(declaredMajor);
        setGeneralEducationCredits(generalEducationCredits);
        setEligibleForMinor(isEligibleForMinor());
    }

    public String getDeclaredMajor() {
        return declaredMajor;
    }

    public void setDeclaredMajor(String declaredMajor) {
        if (declaredMajor == null) {
            throw new IllegalArgumentException("Null values are prohibited.");
        }

        if (declaredMajor.isBlank()) {
            throw new IllegalArgumentException("Your major is required.");
        }

        this.declaredMajor = declaredMajor.trim();
    }

    public int getGeneralEducationCredits() {
        return generalEducationCredits;
    }

    public void setGeneralEducationCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Zero or any negative credits is not allowed!");
        }
        this.generalEducationCredits = credits;
    }

    public boolean isEligibleForMinor() {
        return eligibleForMinor;
    }

    public void setEligibleForMinor(boolean eligibleForMinor) {
        this.eligibleForMinor = eligibleForMinor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n").append("Declared Major: ").append(getDeclaredMajor());
        sb.append("\n").append("General Education Credits: ").append(getGeneralEducationCredits());
        sb.append("\n").append("Eligible for Minor? ").append(isEligibleForMinor() ? "Yes" : "No");

        return sb.toString();
    }
}

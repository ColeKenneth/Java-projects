package studentManagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Junior extends Student {
    private int internshipHours;
    private String specialization;
    private List<String> technicalElectives;

    public Junior(int studentID, String firstName, String middleName, String lastName, int age, YearLevel yearLevel, int internshipHours, String specialization, List<String> technicalElectives) {
        super(studentID, firstName, middleName, lastName, age, yearLevel);
        setInternshipHours(internshipHours);
        setSpecialization(specialization);
        this.technicalElectives = technicalElectives;
    }

    public int getInternshipHours() {
        return internshipHours;
    }

    public void setInternshipHours(int internshipHours) {
        if (internshipHours < 0) {
            throw new IllegalArgumentException("Cannot be any negative value.");
        }

        this.internshipHours = internshipHours;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null) {
            throw new IllegalArgumentException("Cannot be a null value.");
        }

        if (specialization.isBlank()) {
            throw new IllegalArgumentException("Cannot be left blank");
        }

        this.specialization = specialization.trim();
    }

    public List<String> getTechnicalElectives() {
        return technicalElectives;
    }

    public void addTechnicalElectives(List<String> elective) {
        if (elective == null) {
            throw new IllegalArgumentException("Cannot be a null value");
        }

        this.technicalElectives.addAll(elective);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n").append("Internship Hours: ").append(getInternshipHours());
        sb.append("\n").append("Specialization: ").append(getSpecialization());
        sb.append("\n").append("Technical Electives:");

        Iterator<String> electives = technicalElectives.iterator();

        if (electives.hasNext()) {
            while (electives.hasNext()) {
                sb.append("\n  • ").append(electives.next());
            }
        } else {
            sb.append(" None enrolled");
        }

        return sb.toString();
    }
}

package studentManagement;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentStorage {

    public static void addStudent(Student s) {
        String query = "INSERT INTO student_base (first_name, middle_name, last_name, age, year_level) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD)) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtBase = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmtBase.setString(1, s.getFirstName());
                pstmtBase.setString(2, s.getMiddleName());
                pstmtBase.setString(3, s.getLastName());
                pstmtBase.setInt(4, s.getAge());
                pstmtBase.setString(5, s.getYearLevel().toString());
                pstmtBase.executeUpdate();

                try (ResultSet rs = pstmtBase.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedID = rs.getInt(1);

                        insertStudentData(conn, generatedID, s);
                        conn.commit();
                        System.out.println("Student saved to database.");
                    } else {
                        throw new SQLException("Failed to retrieve Student ID.");
                    }
                }


            } catch (SQLException e) {
                conn.rollback();
                System.err.println("An error occurred while saving your data.");
            }
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    private static void insertStudentData(Connection conn, int id, Student s) throws SQLException {
        if (s instanceof Freshman f) {
            String query = "INSERT INTO freshman (student_id, orientation_complete, high_school_origin, entrance_exam_score) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.setBoolean(2, f.isOrientationComplete());
                pstmt.setString(3, f.getHighSchoolOrigin());
                pstmt.setDouble(4, f.getEntranceExamScore());
                pstmt.executeUpdate();
            }
        } else if (s instanceof Sophomore sm) {
            String query = "INSERT INTO sophomore (student_id, declared_major, general_ed_credits, eligible_for_minor) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, sm.getDeclaredMajor());
                pstmt.setInt(3, sm.getGeneralEducationCredits());
                pstmt.setBoolean(4, sm.isEligibleForMinor());
                pstmt.executeUpdate();
            }
        } else if (s instanceof Junior j) {
            String query = "INSERT INTO junior (student_id, internship_hours, specialization) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.setInt(2, j.getInternshipHours());
                pstmt.setString(3, j.getSpecialization());
                pstmt.executeUpdate();
            }

            String electiveQuery = "INSERT INTO junior_electives (student_id, elective_name) VALUES (?, ?)";

            try (PreparedStatement psElective = conn.prepareStatement(electiveQuery, Statement.RETURN_GENERATED_KEYS)) {
                for (String elective : j.getTechnicalElectives()) {
                    psElective.setInt(1, id);
                    psElective.setString(2, elective);
                    psElective.addBatch();
                }
                psElective.executeBatch();
            }
        } else if (s instanceof Senior sr) {
            String query = "INSERT INTO senior (student_id, thesis_title, expected_graduation_date, career_ready) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, sr.getThesisTitle());
                pstmt.setObject(3, sr.getExpectedGraduationDate());
                pstmt.setBoolean(4, sr.isCareerReady());
                pstmt.executeUpdate();
            }
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String viewQuery = "SELECT * FROM list_of_students";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(viewQuery)) {

            while (rs.next()) {
                String yearLevel = rs.getString("year_level");
                Student s = null;

                String fName = rs.getString("first_name");
                String mName = rs.getString("middle_name");
                String lName = rs.getString("last_name");
                int age = rs.getInt("age");

                switch (yearLevel.toUpperCase().trim()) {
                    case "FRESHMAN" -> s = new Freshman(
                            rs.getInt("student_id"), fName, mName, lName, age,
                            YearLevel.valueOf(yearLevel.toUpperCase().trim()),
                            rs.getBoolean("orientation_complete"),
                            rs.getString("high_school_origin"),
                            rs.getDouble("entrance_exam_score"));

                    case "SOPHOMORE" -> s = new Sophomore(
                            rs.getInt("student_id"),
                            fName, mName, lName, age,
                            YearLevel.valueOf(yearLevel.toUpperCase().trim()),
                            rs.getString("declared_major"),
                            rs.getInt("general_ed_credits"),
                            rs.getBoolean("eligible_for_minor"));

                    case "JUNIOR" -> {
                        int id = rs.getInt("student_id");

                        List<String> electives = getJuniorElectives(id, conn);
                        s = new Junior(id, fName, mName, lName, age,
                                YearLevel.valueOf(yearLevel.toUpperCase().trim()),
                                rs.getInt("internship_hours"),
                                rs.getString("specialization"),
                                electives);
                    }

                    case "SENIOR" -> s = new Senior(
                            rs.getInt("student_id"),
                            fName, mName, lName, age,
                            YearLevel.valueOf(yearLevel.toUpperCase().trim()),
                            rs.getString("thesis_title"),
                            rs.getObject("expected_graduation_date", LocalDate.class),
                            rs.getBoolean("career_ready")
                    );

                }
                if (s != null) students.add(s);

            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        return students;
    }

    private static List<String> getJuniorElectives(int studentId, Connection conn) throws SQLException {
        List<String> electives = new ArrayList<>();
        String sql = "SELECT elective_name FROM junior_electives WHERE student_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    electives.add(rs.getString("elective_name"));
                }
            }
        }
        return electives;
    }

    public static boolean updateStudentName(int studentID, String firstname, String middlename, String lastname) {
        String query = "UPDATE student_base SET first_name = ?, middle_name = ?, last_name = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, middlename);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, studentID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated student successfully!");
                return true;
            } else {
                System.out.println("No student found!");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateStudentAge(int studentID, int age) {
        String query = "UPDATE student_base SET age = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, age);
            pstmt.setInt(2, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated age successfully!");
                return true;
            } else {
                System.out.println("No student found!");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateFreshman(int studentID, boolean orientationComplete, String highSchoolOrigin, double entranceExamScore) {
        String query = "UPDATE freshman SET orientation_complete = ?, high_school_origin = ?, entrance_exam_score = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, orientationComplete);
            pstmt.setString(2, highSchoolOrigin);
            pstmt.setBigDecimal(3, BigDecimal.valueOf(entranceExamScore));
            pstmt.setInt(4, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Freshman student updated!");
                return true;
            } else {
                System.out.println("No student found!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateSophomore(int studentID, String declaredMajor, int generalEducationCredits, boolean eligibleForMinor) {
        String query = "UPDATE sophomore SET declared_major = ?, general_ed_credits = ?, eligible_for_minor = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, declaredMajor);
            pstmt.setInt(2, generalEducationCredits);
            pstmt.setBoolean(3, eligibleForMinor);
            pstmt.setInt(4, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sophomore student updated!");
                return true;
            } else {
                System.out.println("No student found!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateJunior(int studentID, int internshipHours, String specialization) {
        String query = "UPDATE junior SET internship_hours = ?, specialization = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, internshipHours);
            pstmt.setString(2, specialization);
            pstmt.setInt(3, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated junior student");
                return true;
            } else {
                System.out.println("Student not found!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateElectives(int studentID, List<String> electives) {
        String deleteQuery = "DELETE FROM junior_electives WHERE student_id = ?";
        String insertQuery = "INSERT INTO junior_electives (student_id, elective_name) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD)) {
            conn.setAutoCommit(false); 

            try (PreparedStatement deletePstmt = conn.prepareStatement(deleteQuery);
                 PreparedStatement insertPstmt = conn.prepareStatement(insertQuery)) {

                deletePstmt.setInt(1, studentID);
                deletePstmt.executeUpdate();

                for (String elective : electives) {
                    insertPstmt.setInt(1, studentID);
                    insertPstmt.setString(2, elective);
                    insertPstmt.addBatch();
                }
                insertPstmt.executeBatch();
                conn.commit();
                System.out.println("Electives updated successfully!");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Update failed. Transaction rolled back: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Connection Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateSenior(int studentID, String thesisTitle, LocalDate expectedGraduationDate, boolean careerReady) {
        String query = "UPDATE senior SET thesis_title = ?, expected_graduation_date = ?, career_ready = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, thesisTitle);
            pstmt.setObject(2, expectedGraduationDate);
            pstmt.setBoolean(3, careerReady);
            pstmt.setInt(4, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated senior student successfully!");
                return true;
            } else {
                System.out.println("Student not found!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteStudent(int studentID) {
        String query = "DELETE FROM student_base WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(Config.DB_PATH, Config.USERNAME, Config.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
                return true;
            } else {
                System.out.println("Student not found!");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }

    }
}
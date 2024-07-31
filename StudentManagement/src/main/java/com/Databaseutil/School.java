package com.Databaseutil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class School {

	public void addStudent(Student student) {
	    String query = "INSERT INTO students (name) VALUES (?)";
	    try (Connection connection = DatabaseUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setString(1, student.getName());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


    public void addSubjectAndGrade(int studentId, String subject, int grade) {
        String query = "INSERT INTO subjects (student_id, subject_name, grade) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, subject);
            preparedStatement.setInt(3, grade);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                students.add(new Student(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public double calculateAverageGrade(int studentId) {
        String query = "SELECT AVG(grade) AS average FROM subjects WHERE student_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLowestGrade(int studentId) {
        String query = "SELECT MIN(grade) AS lowest FROM subjects WHERE student_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("lowest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getHighestGrade(int studentId) {
        String query = "SELECT MAX(grade) AS highest FROM subjects WHERE student_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("highest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Student> getStudentsWithSubjects() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT s.id, s.name, GROUP_CONCAT(sub.subject_name ORDER BY sub.id SEPARATOR ', ') AS subjects " +
                       "FROM students s " +
                       "LEFT JOIN subjects sub ON s.id = sub.student_id " +
                       "GROUP BY s.id, s.name";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String subjects = resultSet.getString("subjects");
                students.add(new Student(id, name, subjects));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
    
	

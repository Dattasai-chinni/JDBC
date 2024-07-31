package com.Databaseutil;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        School school = new School();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student\n2. Add Subject and Grade\n3. Display Students\n4. Calculate Average Grade\n5. Display Lowest and Highest Grades\n6. display_detials\n7.exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter student name:");
                    String name = scanner.nextLine();
                    school.addStudent(new Student(name));
                    break;
                case 2:
                    System.out.println("Enter student ID:");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter subject:");
                    String subject = scanner.nextLine();
                    System.out.println("Enter grade:");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    school.addSubjectAndGrade(studentId, subject, grade);
                    break;
                case 3:
                    List<Student> students = school.getStudents();
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;
                case 4:
                    System.out.println("Enter student ID:");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    double average = school.calculateAverageGrade(studentId);
                    System.out.println("Average grade: " + average);
                    break;
                case 5:
                    System.out.println("Enter student ID:");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    int lowest = school.getLowestGrade(studentId);
                    int highest = school.getHighestGrade(studentId);
                    System.out.println("Lowest grade: " + lowest);
                    System.out.println("Highest grade: " + highest);
                    break;
                case 6:
                    List<Student> allStudentsWithSubjects = school.getStudentsWithSubjects();
                    for (Student student : allStudentsWithSubjects) {
                    	System.out.println("Student ID :"+student.getId());
                        System.out.println("Student Name: " + student.getName());
                        System.out.println("Subjects: " + student.getSubjects());
                        System.out.println();  
                    }
                    break;    
                case 7:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

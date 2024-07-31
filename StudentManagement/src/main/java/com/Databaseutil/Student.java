package com.Databaseutil;


public class Student {
    private int id;
    private String name;
    private String subjects;
    
    
    public String getSubjects() {
    	return subjects;
    }

    public Student(int id, String name,String subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name+"subjects :"+subjects;
    }
}



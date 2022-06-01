package task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();


    public University(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student result = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                result = student;
                break;
            }
        };
        return result;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student result = null;
        double averageGrade = 0;
        for (Student student : students) {
            if (student.getAverageGrade() > averageGrade) {
                result = student;
                averageGrade = student.getAverageGrade();
            }
        };
        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        Student result = null;
        double averageGrade = students.get(0).getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() < averageGrade) {
                result = student;
                averageGrade = student.getAverageGrade();
            }
        };
        return result;
    }


    public void expel(Student student) {
        students.remove(student);
    }

    /*public void getStudentWithMinAverageGradeAndExpel() {
        //TODO:
    }*/
}
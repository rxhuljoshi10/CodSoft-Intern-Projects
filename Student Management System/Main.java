import java.util.*;
import java.io.*;

class Student implements Serializable{
    int rno;
    String name;
    // long number;
    String grade;

    public Student(int rno, String name, String grade){
        this.rno = rno;
        this.name = name;
        this.grade = grade;
    }

    String getName(){ return name; }

    int getRno(){ return rno; }

    String getGrade() {return grade; } 

    void setName(String name){
        this.name = name;
    }
    void setRno(int rno){
        this.rno = rno;
    }
    void setGrade(String grade){
        this.grade = grade;
    }
}

class StudentManagementSystem{
    private List<Student> studentsList;
    private final String dataFile = "C:\\Users\\joshi\\Documents\\Programs\\CodSoft-Intern-Projects\\Student Management System\\students.dat";
    StudentManagementSystem(){
        studentsList = new ArrayList<>();
        fetchStudentsData();
    }

    public void addStudent(Student student){
        studentsList.add(student);
        saveStudentsData();
    }

    public void removeStudent(int rno){
        studentsList.removeIf(student -> student.getRno() == rno);
        saveStudentsData();
    }

    public Student searchStudent(int rno){
        for(Student student : studentsList){
            if(student.getRno() == rno) return student;
        }
        return null;
    }

    public List<Student> getAllStudents(){
        return studentsList;
    }

    public void fetchStudentsData(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            studentsList = (List<Student>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudentsData(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))){
            oos.writeObject(studentsList);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Main{
    static Scanner scanner;
    static StudentManagementSystem system;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
        system = new StudentManagementSystem();

        while(true){
            System.out.println("\n\t*Student Management System*\n");
            System.out.println("1 : Add Student");
            System.out.println("2 : Remove Student");
            System.out.println("3 : Search Student");
            System.out.println("4 : Display Students");
            System.out.println("5 : Exit");
            System.out.print("\nEnter your choice : ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    inputStudentDetails();
                    break;
                case 2:
                    removeStudentInput();
                    break;
                case 3:
                    searchStudentInput();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }

    private static void inputStudentDetails(){
        System.out.print("\033[H\033[2J");
        System.out.println("\tEnter Student Details\n");
        System.out.print("Name : ");
        String name = scanner.next();

        int rno;
        while(true){
            System.out.print("Roll Number : ");
            try{
                rno = scanner.nextInt();
                break;
            } 
            catch(Exception e){
                System.out.println("Invalid Roll Number! Please enter again.");
            }
        }

        System.out.print("Grade : ");
        String grade = scanner.next();

        Student student = new Student(rno, name, grade);
        system.addStudent(student);
        System.out.println("\nSuccessfully Added Student!");
    }

    private static void removeStudentInput(){
        System.out.print("\nEnter Roll number of student to be removed : ");
        int rno = scanner.nextInt();
        system.removeStudent(rno);
        System.out.println("\nStudent removed successfully!");
    }

    private static void searchStudentInput(){
        System.out.print("\nEnter Roll number of student to be removed : ");
        int rno = scanner.nextInt();
        Student student = system.searchStudent(rno);
        if(student != null){
            System.out.println("\n"+student.name);
        }
        else{
            System.out.println("\nStudent Not Found!");
        }
    }
    public static void displayStudents(){
        System.out.print("\033[H\033[2J");
        for(Student student : system.getAllStudents()){
            System.out.println(student.name);
        }
    }
}
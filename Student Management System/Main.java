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

    public boolean removeStudent(int rno){
        int studentCount = studentsList.size();
        studentsList.removeIf(student -> student.getRno() == rno);
        if(studentsList.size() < studentCount){
            saveStudentsData();
            return true;
        }
        return false;
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
            // e.printStackTrace();
        }
    }

    public void saveStudentsData(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))){
            oos.writeObject(studentsList);
        }catch (Exception e) {
            // e.printStackTrace();
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
            clearScreen();
            System.out.println("\n\t*Student Management System*");
            System.out.println("\n1 : Add Student");
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
            showContinueOption();
        }
    }

    private static void inputStudentDetails(){
        clearScreen();
        System.out.println("\n\tEnter Student Details\n");
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
                System.out.println("\nInvalid Roll Number! Please enter again.");
                scanner.next();
            }
        }

        System.out.print("Grade : ");
        String grade = scanner.next();

        Student student = new Student(rno, name, grade);
        system.addStudent(student);
        System.out.println("\nSuccessfully Added Student!");
    }

    private static void removeStudentInput(){
        clearScreen();
        System.out.print("\nEnter Roll number of student to be removed : ");
        int rno = scanner.nextInt();
        if(system.removeStudent(rno)){
            System.out.println("\nStudent removed successfully!");
        }
        else{
            System.out.println("\nStudent not found!");
        }
    }

    private static void searchStudentInput(){
        clearScreen();
        System.out.print("\nEnter Roll number of student to be searched : ");
        int rno = scanner.nextInt();
        Student student = system.searchStudent(rno);
        if(student != null){
            displayStudent(student, 1);
        }
        else{
            System.out.println("\nStudent Not Found!");
        }
    }

    public static void displayStudents(){
        clearScreen();
        System.out.println("\n\tStudents List");
        System.out.println("");
        int index = 1;
        for(Student student : system.getAllStudents()){
            displayStudent(student, index++);
        }
    }

    public static void displayStudent(Student student, int index){
        System.out.println(index+". "+student.name+"\t"+student.rno+"\t"+student.grade);
    }

    public static void showContinueOption(){
        System.out.print("\n\nPress 'Enter' to continue..! ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
    }
}
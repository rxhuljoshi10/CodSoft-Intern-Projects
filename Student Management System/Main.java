import java.util.*;
import java.io.*;

class Student implements Serializable{
    int rno;
    String name;
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

    public void modifyStudent(Student student){
        removeStudent(student.rno);
        addStudent(student);
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
            System.out.println("5 : Modify Student");
            System.out.println("6 : Exit");
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
                    modifyStudentInput();
                    break;
                case 6:
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

    public static void modifyStudentInput(){
        clearScreen();
        System.out.print("\nEnter Roll number of student to be edited : ");
        int rno = scanner.nextInt();
        Student student = system.searchStudent(rno);
        if(student != null){
            displayStudent(student, 1);
            modifyDetails(student);
            System.out.println("\nModified Succesfully!");
        }
        else{
            System.out.println("\nStudent Not Found!");
        }
    }

    public static void modifyDetails(Student student){
        System.out.println("\n1 : Name ");
        System.out.println("2 : Roll Number ");
        System.out.println("3 : Grade ");
        System.out.print("\nChoose what to modify? : ");
        int choice = scanner.nextInt(); 
        switch(choice){
            case 1:
                System.out.print("Enter New Name : ");
                student.setName(scanner.next());
                break;
            case 2:
                System.out.print("Enter New Roll Number : ");
                student.setRno(scanner.nextInt());
                break;
            case 3:
                System.out.print("Enter New Grade : ");
                student.setGrade(scanner.next());
                break;
        }
        system.modifyStudent(student);
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
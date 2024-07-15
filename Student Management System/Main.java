import java.util.*;

class Student{
    int rno;
    String name;
    // long number;
    String grade;

    public Student(int rno, String name, String grade){
        rno = rno;
        name = name;
        grade = grade;
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


class Main{
    static Scanner scanner;
    public static void main(String[] args){
        scanner = new Scanner(System.in);

        while(true){
            System.out.print("\033[H\033[2J");
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
        System.out.println("\nSuccessfully Added Student!");
    }
}
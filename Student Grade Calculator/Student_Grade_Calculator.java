import java.util.*;

class Student{
    private double totalMarks;
    private double avgPercent;
    private String grades;
    private double[] marks;

    Student(int subjects){
        marks = new double[subjects];
    }

    public void inputMarks(int stud_Id){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Marks of student "+stud_Id+" : ");
        for(int i=0;i<marks.length;i++){
            System.out.print("Subject "+(i+1)+" : ");
            marks[i] = sc.nextDouble();
        }
    }

    public void calcTotalMarks(){
        for(double mark : marks){
            totalMarks += mark;
        }
    }

    public void calcAvg(){
        avgPercent = totalMarks / marks.length;
    }

    public void calcGrade(){
        if(avgPercent >= 90){
            grades = "A+";
        }
        else if(avgPercent >= 80){
            grades = "A";
        }
        else if(avgPercent >= 70){
            grades = "B";
        }
        else if(avgPercent >= 60){
            grades = "C";
        }
        else if(avgPercent >= 50){
            grades = "D";
        }
        else grades = "F";
    }

    public void displayDetails(){
        System.out.printf("Total Marks : %.2f", totalMarks);
        System.out.printf("\nAverage Percentage : %.2f", avgPercent);
        System.out.println("\nGrade : " + grades);
    }
}

class Student_Grade_Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int subjects = 3;
        System.out.print("\nEnter the number of Students : ");
        int sCap = sc.nextInt();
        Student[] stud = new Student[sCap];

        for(int i=0;i<sCap;i++){
            stud[i] = new Student(subjects);
            stud[i].inputMarks(i+1);
            stud[i].calcTotalMarks();
            stud[i].calcAvg();
            stud[i].calcGrade();
        }

        System.out.println("\n\n\tStudent Grade Details");
        for(int i=0;i<sCap;i++){
            System.out.println("\nStudent "+(i+1));
            stud[i].displayDetails();
        }
    }
}
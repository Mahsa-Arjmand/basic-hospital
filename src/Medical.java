import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Medical {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();

        while (true) {
            System.out.println("Enter credentials \n");
            System.out.println("Enter username :");
            String username = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();

            UserCredentials userCredentials = new UserCredentials(username, password);

            if (userCredentials.getUsername().equals(doctor.getUsername()) && userCredentials.getPassword().equals(doctor.getPassword())) {
                doctor.viewPatientsInfo();
                break;
            } else if (userCredentials.getUsername().equals(nurse.getUsername()) && userCredentials.getPassword().equals(nurse.getPassword())) {
                nurse.addPatientInfo();
                break;
            } else {
                System.out.println("Invalid credentials! please try again. \b");
            }

        }
    }
}

class UserCredentials {
    private  String username;
    private String password;

    public UserCredentials(String username , String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Doctor {
    private String username = "doctor";
    private  String password = "password";



    public void viewPatientsInfo()throws IOException{
        File file = new File("patients.txt");

        if(!file.exists()){
            System.out.println("No patients record found.");
            return;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return  username;
    }
}


class Nurse {
    private String username = "nurse";
    private  String password = "password2";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addPatientInfo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.println("Enter patient name :");
            String name = scanner.nextLine();
            System.out.println("Enter patient last name :");
            String lastName = scanner.nextLine();
            System.out.println("Enter patient age :");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter patient address :");
            String address = scanner.nextLine();
            System.out.println("Enter patient phone number :");
            String phoneNumber = scanner.nextLine();

            Patient patient = new Patient(name, lastName, age, address, phoneNumber);
            addPatientToFile(patient);
            System.out.println("Patient info successfully saved");

            System.out.println("Do you want to continue ? (y/n)");
            String again = scanner.nextLine();
            switch (again){
                case "y":
                    flag = true;
                    break;
                case "n":
                    flag = false;
                    break;
                default:
                    System.out.println("Enter y or n");
            }
        }while (flag);
    }
    private void addPatientToFile(Patient patient) throws IOException {
        FileWriter fileWriter = new FileWriter("patients.txt" , true);
        fileWriter.write(patient.toString() + "\n");
        fileWriter.close();
    }
}


class Patient {
    private String name;
    private String lastName;
    private int age;
    private String address;
    private String phoneNumber;

    public Patient(String name , String lastName , int age , String address , String phoneNumber ) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}




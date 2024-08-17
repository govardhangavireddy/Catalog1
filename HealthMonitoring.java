package Hackathone1;

import java.util.*;

public class HealthMonitoring {
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        Manager mgr = new Manager();

        while(true){
            System.out.println("List of Available Options");
            System.out.println("1. Add Patient\n2. Add Medical Records to existing Patient\n3. Display Patient Records\n4. Display the Records of all the Patients\n5. Exit");
            System.out.print("Enter your Choice : ");
            int ch = x.nextInt();
            x.nextLine();
            System.out.println();
            switch(ch){
                case 1:
                    System.out.println("Fill the below details");
                    System.out.print("Enter Patient's Name : ");
                    String name = x.nextLine();
                    System.out.print("Enter Patient's Age : ");
                    int age = x.nextInt();
                    x.nextLine();
                    System.out.print("Enter Patient's Gender[M/F] :");
                    char gender = x.next().charAt(0);
                    Random rand = new Random();
                    int patient_ID = rand.nextInt(10000);
                    while(mgr.findPatient(patient_ID)!=null){
                        patient_ID = rand.nextInt(10000);
                    }
                    System.out.println("Adding new Patient....");
                    mgr.addPatient(new Patient(patient_ID, name, age, gender));
                    System.out.printf("New patient %s's ID is %d\n",name,patient_ID);
                    System.out.printf( "Patient %s's details added successfully!\n\n",name);
                    break;
                case 2:
                    System.out.println("Adding Medical Records");
                    System.out.print("Enter Patient's ID:");
                    int ID = x.nextInt();
                    Patient patient = mgr.findPatient(ID);
                    while(patient==null){
                        System.out.println("Patient not found.Please enter the correct ID\n");
                        System.out.print("Enter Patient's ID:");
                        ID = x.nextInt();
                        patient = mgr.findPatient(ID);
                    }
                    x.nextLine();
                    System.out.print("Enter the medical record : ");
                    String record = x.nextLine();
                    patient.addMedicalRecords(record);
                    System.out.printf("Adding Medical Records to Patient %d\n",ID);
                    System.out.println("Medical record added successfully.\n");
                    break;

                case 3:
                    System.out.print("Enter Patient's ID: ");
                    int p_ID = x.nextInt();
                    Patient foundPatient = mgr.findPatient(p_ID);
                    if (foundPatient != null) {
                        System.out.println(foundPatient);
                    } else {
                        System.out.println("Patient not found.\n");
                    }
                    break;

                case 4:
                    mgr.displayAllPatients();
                    break;

                case 5:
                    System.out.println("Exiting the system.....");
                    x.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.\n");

            }
        }

    }    
}

class Manager{
    private List<Patient> patients;

    public Manager(){
        patients = new ArrayList<>();
    }

    public void addPatient(Patient patient){
        patients.add(patient);
    }

    public Patient findPatient(int ID){
        for(Patient patient:patients){
            if(patient.get_Patient_ID()==ID){
                return patient;
            }
        }
        return null;
    }
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients in the system.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }
}

class Patient{
    private int p_ID;
    private String p_Name;
    private int p_Age;
    private char p_gender;
    private List<String> medicalHistory;

    public Patient(int p_ID,String name,int age,char g){
        this.p_ID = p_ID;
        this.p_Name = name;
        this.p_Age = age;
        this.p_gender = g;
        this.medicalHistory = new ArrayList<>();
    }

    public int get_Patient_ID(){
        return p_ID;
    }
    

    public String get_Patient_Name(){
        return p_Name;
    }

    public int get_Patient_Age(){
        return p_Age;
    }
    
    public char get_Patient_gender(){
        return p_gender;
    }

    public List<String> getMedicalHistory(){
        return medicalHistory;
    }
    
    public void addMedicalRecords(String record){
        medicalHistory.add(record);
    }
    
    @Override
    public String toString() {
        return "Patient ID : "+p_ID+"\nPatient Name: " + p_Name + "\nAge: " + p_Age + "\nGender: " + p_gender + "\nMedical History: " + medicalHistory + "\n";
    }

}

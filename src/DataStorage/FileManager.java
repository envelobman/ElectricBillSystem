package DataStorage;

import java.io.*;
import java.util.*;


public class FileManager {
          
              //FOR CUSTOMERS//

public static List<String> readCustomers(){
    return readFile("customers.txt");
}

public static void writeCustomers(List<String> lines){
    writeFile("customers.txt",lines);
}

public static List<String>  readBills(){
    return readFile("bills.txt");
}

public static void writeBills(List<String> lines){
    writeFile("bills.txt",lines);
}
    
// ----- METERS -----
public static List<String> readMeters() {
    return readFile("meters.txt");
}

public static void writeMeters(List<String> lines) {
    writeFile("meters.txt", lines);
}

// ----- COMPLAINTS -----
public static List<String> readComplaints() {
    return readFile("complaints.txt");
}

public static void writeComplaints(List<String> lines) {
    writeFile("complaints.txt", lines);
}

// ----- USERS -----
public static List<String> readUsers() {
    return readFile("users.txt");
}

public static void writeUsers(List<String> lines) {
    writeFile("users.txt", lines);
}
    


               //FILE FUNCTIONS//

    public static List<String> readFile(String filename){
    List<String> lines= new ArrayList<>();
    try(BufferedReader br= new BufferedReader(new FileReader(filename))){
        String line;
        while ((line=br.readLine())!=null){
            lines.add(line);        }
    }
    catch (IOException e){
        System.out.println("ERROR READING FILE "+filename+":"+e.getMessage());
    }
    return lines;}
    
    public static void writeFile(String filename,List<String> lines){
    try(BufferedWriter bw= new BufferedWriter(new FileWriter(filename))){
            for(String line:lines){
                bw.write(line);
                bw.newLine();
            }}
    catch (IOException e){
             System.out.println("ERROR WRITING FILE "+filename+":"+e.getMessage());
                }    
        }

    public static void updateCustomer(String meterCode, String newLine) {
    List<String> customers = readCustomers(); // read existing lines
    for (int i = 0; i < customers.size(); i++) {
        if (customers.get(i).startsWith(meterCode + ",")) {
            customers.set(i, newLine); // update line
            break;
        }
    }
    writeCustomers(customers); 
    }

    public static void deleteCustomer(String meterCode) {
    List<String> customers = readCustomers();
    customers.removeIf(line -> line.startsWith(meterCode + ",")); 
    writeCustomers(customers); 
    }

    public static void updateBill(String billID, String newLine) {
    List<String> bills = readBills();
    for (int i = 0; i < bills.size(); i++) {
        if (bills.get(i).startsWith(billID + ",")) {
            bills.set(i, newLine);
            break;
        }
    }
    writeBills(bills);
    }

    public static void deleteBill(String billID) {
    List<String> bills = readBills();
    bills.removeIf(line -> line.startsWith(billID + ","));
    writeBills(bills);
    }
 
    public static void updateUser(String username, String newLine) {
    List<String> users = readUsers();
    for (int i = 0; i < users.size(); i++) {
        if (users.get(i).startsWith(username + ",")) {
            users.set(i, newLine);
            break;
        }
    }
    writeUsers(users);
}

public static void deleteUser(String username) {
    List<String> users = readUsers();
    users.removeIf(line -> line.startsWith(username + ","));
    writeUsers(users);
}

// ---------- TESTING ----------
    public static void main(String[] args) {
       List<String> customers = readCustomers();

         //Test writing (In Customers txt file)
        customers.add("123,Ali,Street 5,ali@email.com,320,380,");
        customers.add("124,Ali,Street 5,ali@email.com,320,380,");
        customers.add("125,Ali,Street 5,ali@email.com,320,380,");
        writeCustomers(customers);
    
////        //Test Updating customer
String newLine = "1024,Mohamed Saleh,Street 10,mohamed@gmail.com,100,180"; 
    updateCustomer("123", newLine);
    
//    //  Test deleting Customer
    deleteCustomer("124");

// Test reading and printing file
    customers=readCustomers();
    for (String c : customers) {
            System.out.println(c);
        }
             
   
    
    }

    public void append(String FILE, String toString) {
//        ??
    }
}




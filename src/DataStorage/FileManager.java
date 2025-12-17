package DataStorage;

import Model.Bill;
import Model.Complaint;
import Model.Customer;
import Model.Meter;
import Model.NewCustomer;
import Model.OldCustomer;
import java.io.*;
import java.util.*;

public class FileManager {

    // ---------- GENERIC ----------
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists())
            return lines;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);
        } catch (IOException e) {
            System.out.println("ERROR READING " + filename);
        }
        return lines;
    }

    public static void writeFile(String filename, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR WRITING " + filename);
        }
    }

    // ---------- CUSTOMERS ----------
    public static List<Customer> readCustomers() {
        List<Customer> list = new ArrayList<>();
        for (String line : readFile("customers.txt")) {
            String[] p = line.split("\\|");

            if (p[7].equals("old"))
                list.add(new OldCustomer(
                        p[0], p[1], p[2], p[3],
                        p[4], p[5], p[6],
                        Double.parseDouble(p[8])
                ));
            else
                list.add(new NewCustomer(
                        p[0], p[1], p[2], p[3],
                        p[4], p[5], p[6],
                        p[8]
                ));
        }
        return list;
    }

    public static void writeCustomers(List<Customer> customers) {
        List<String> lines = new ArrayList<>();
        for (Customer c : customers)
            lines.add(c.toString());
        writeFile("customers.txt", lines);
    }

    public static void updateCustomer(Customer updated) {
        List<Customer> customers = readCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(updated.getId())) {
                customers.set(i, updated);
                break;
            }
        }
        writeCustomers(customers);
    }

    public static void deleteCustomer(String nationalId) {
        List<Customer> customers = readCustomers();
        customers.removeIf(c -> c.getId().equals(nationalId));
        writeCustomers(customers);
    }

    // ---------- BILLS ----------
    public static List<Bill> readBills() {
        List<Bill> bills = new ArrayList<>();
        for (String line : readFile("bills.txt")) {
            String[] p = line.split("\\|");
            bills.add(new Bill(
                    p[0], p[1], p[2], p[3],
                    Double.parseDouble(p[4]),
                    Double.parseDouble(p[5]),
                    Double.parseDouble(p[6]),
                    Boolean.parseBoolean(p[7])
            ));
        }
        return bills;
    }

    public static void writeBills(List<Bill> bills) {
        List<String> lines = new ArrayList<>();
        for (Bill b : bills)
            lines.add(b.toString());
        writeFile("bills.txt", lines);
    }

    // ---------- METERS ----------
    public static List<Meter> readMeters() {
        List<Meter> meters = new ArrayList<>();
        for (String line : readFile("meters.txt")) {
            String[] p = line.split("\\|");
            meters.add(new Meter(
                    p[0],
                    Double.parseDouble(p[1]),
                    Boolean.parseBoolean(p[2])
            ));
        }
        return meters;
    }

    public static void writeMeters(List<Meter> meters) {
        List<String> lines = new ArrayList<>();
        for (Meter m : meters)
            lines.add(m.toString());
        writeFile("meters.txt", lines);
    }

    // ---------- COMPLAINTS ----------
    public static List<Complaint> readComplaints() {
        List<Complaint> list = new ArrayList<>();
        for (String line : readFile("complaints.txt")) {
            String[] p = line.split("\\|");
            list.add(new Complaint(p[0], p[1], p[2]));
        }
        return list;
    }

    public static void writeComplaints(List<Complaint> complaints) {
        List<String> lines = new ArrayList<>();
        for (Complaint c : complaints)
            lines.add(c.toString());
        writeFile("complaints.txt", lines);
    }
}

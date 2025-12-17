package Controller;

import DataStorage.*;
import Model.*;
import java.util.*;

public class CustomerController {

    // ---------- GET CUSTOMER BY METER ----------
    public Customer getCustomer(String meterCode) {
        for (Customer c : FileManager.readCustomers()) {
            if (c.getMeterCode().equals(meterCode))
                return c;
        }
        return null;
    }

    // ---------- ADD CUSTOMER ----------
    public String addCustomer(Customer c) {

        if (Validation.isEmpty(c.getfirstName())) return "First name required!";
        if (Validation.isEmpty(c.getsecondName())) return "Second name required!";
        if (!Validation.isValidEmail(c.getEmail())) return "Invalid Email!";
        if (Validation.isEmpty(c.getMeterCode())) return "Meter code missing!";

        List<Customer> customers = FileManager.readCustomers();
        customers.add(c);
        FileManager.writeCustomers(customers);

        return "Customer Added";
    }
    public String EditCustomer(Customer updated) {
    List<Customer> customers = FileManager.readCustomers();
    boolean found = false;

    for (int i = 0; i < customers.size(); i++) {
        Customer c = customers.get(i);
        if (c.getMeterCode().equals(updated.getMeterCode())) {
            // Update بيانات الكستومر
            customers.set(i, updated);
            found = true;
            break;
        }
    }

    if (!found) return "Customer not found";

    FileManager.writeCustomers(customers);
    return "Customer info updated";
}
    public String deleteCustomer(String meterCode) {
    List<Customer> customers = FileManager.readCustomers();
    boolean removed = customers.removeIf(c -> c.getMeterCode().equals(meterCode));

    if (!removed) return "Customer not found";

    FileManager.writeCustomers(customers);
    return "Customer deleted";
}
    // ---------- UPDATE READING ----------
    public String updateReading(String meterCode, double newReading) {

        if (!Validation.isPositive(newReading))
            return "Reading must be positive";

        List<Customer> customers = FileManager.readCustomers();
        boolean found = false;

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            if (c.getMeterCode().equals(meterCode)) {
                if (newReading < c.getLastReading())
                    return "New reading < last reading";

                c.setLastReading(newReading);
                customers.set(i, c);
                found = true;
                break;
            }
        }

        if (!found) return "Meter not found";

        FileManager.writeCustomers(customers);
        return "Reading updated";
    }

    // ---------- SEARCH CUSTOMER INFO ----------
    public Customer searchCustomer(String meterCode) {
        return getCustomer(meterCode);
    }

    // ---------- SAVE METER READING ----------
    public void saveReading(Meter m) {
        List<Meter> meters = FileManager.readMeters();
        boolean found = false;

        for (int i = 0; i < meters.size(); i++) {
            if (meters.get(i).getMeterCode().equals(m.getMeterCode())) {
                meters.set(i, m);
                found = true;
                break;
            }
        }

        if (!found) {
            meters.add(m);
        }

        FileManager.writeMeters(meters);
    }

    // ---------- GET ALL CUSTOMERS ----------
    public List<Customer> getAllCustomers() {
        return FileManager.readCustomers();
    }
    public String[] searchForCustomer(String meterCode) {

    String[] result = new String[15];

    // default values
    for (int i = 0; i < result.length; i++)
        result[i] = "N/A";

    // ---------- CUSTOMERS ----------
    for (String line : FileManager.readFile("customers.txt")) {
        String[] p = line.split("\\|");

        if (p[6].equals(meterCode)) {
            result[0] = p[0]; // nationalId
            result[1] = p[1]; // firstName
            result[2] = p[2]; // lastName
            result[3] = p[3]; // phone
            result[4] = p[4]; // region
            result[5] = p[5]; // email
            result[6] = p[6]; // meterCode
            result[8] = p[8]; // customerStatus
            result[9] = p[9]; // contract
            result[11] = p[11]; // lastReading
            break;
        }
    }

    // ---------- METERS ----------
    for (String line : FileManager.readFile("meters.txt")) {
        String[] p = line.split("\\|");

        if (p[0].equals(meterCode)) {
            result[10] = p[1]; // reading
            break;
        }
    }

    // ---------- BILLS ----------
    for (String line : FileManager.readFile("bills.txt")) {
        String[] p = line.split("\\|");

        if (p[1].equals(meterCode)) {
            result[7]  = p[7];  // billStatus
            result[12] = p[6];  // tariff
            result[13] = p[2];  // date
            result[14] = p[5];  // bill value
            break;
        }
    }

    return result;
}
    
    public String addMonthlyReading(String meterCode, double monthlyReading) {

    if (Validation.isEmpty(meterCode))
        return "Meter code is required";

    if (!Validation.isPositive(monthlyReading))
        return "Reading must be positive";

    String line = meterCode + "|" + monthlyReading;

    List<String> readings = FileManager.readFile("bills.txt");
    readings.add(line);
    FileManager.writeFile("bills.txt", readings);

    return "Monthly reading saved successfully";
}
    
public void setNewReading(String meterCode, double monthlyReading) {


    String line = meterCode + "|" + monthlyReading;

    List<String> readings = FileManager.readFile("bills.txt");
    readings.add(line);
    FileManager.writeFile("bills.txt", readings);
}

}

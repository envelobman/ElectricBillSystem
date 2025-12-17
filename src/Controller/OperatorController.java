package Controller;

import DataStorage.*;
import Model.*;
import java.util.*;

public class OperatorController {

    // ---------- UPDATE METER READING ----------
    public String updateMeter(Meter m, double newReading) {

        if (!Validation.isPositive(newReading))
            return "Invalid Reading";

        m.updateReading(newReading);
        saveMeter(m);
        return "Meter updated";
    }

    // ---------- ACTIVATE / DEACTIVATE ----------
    public String activateMeter(Meter m) {
        m.activate();
        saveMeter(m);
        return "Meter activated";
    }

    public String deactivateMeter(Meter m) {
        m.deactivate();
        saveMeter(m);
        return "Meter deactivated";
    }

    // ---------- CANCEL CUSTOMER ----------
    public String cancelCustomer(String meterCode) {
        List<Customer> customers = FileManager.readCustomers();
        boolean removed = customers.removeIf(c -> c.getMeterCode().equals(meterCode));
        if (removed) {
            FileManager.writeCustomers(customers);
            return "Customer cancelled";
        }
        return "Customer not found";
    }

    // ---------- SEARCH CUSTOMER ----------
    public Customer searchCustomer(String meterCode) {
        for (Customer c : FileManager.readCustomers()) {
            if (c.getMeterCode().equals(meterCode))
                return c;
        }
        return null;
    }

    // ---------- SAVE METER ----------
    public void saveMeter(Meter m) {
        List<Meter> meters = FileManager.readMeters();
        boolean found = false;

        for (int i = 0; i < meters.size(); i++) {
            if (meters.get(i).getMeterCode().equals(m.getMeterCode())) {
                meters.set(i, m);
                found = true;
                break;
            }
        }

        if (!found) meters.add(m);
        FileManager.writeMeters(meters);
    }

    // ---------- GET ALL METERS ----------
    public List<Meter> getAllMeters() {
        return FileManager.readMeters();
    }
    public String setCustomerStatus(String customerStatus) {

    // validation
    if (customerStatus == null || customerStatus.isEmpty())
        return "Invalid status";

    if (!customerStatus.equalsIgnoreCase("activate") &&
        !customerStatus.equalsIgnoreCase("deactivate") &&
        !customerStatus.equalsIgnoreCase("cancel"))
        return "Unknown status";

    String status = customerStatus.toLowerCase();
    
    List<String> lines = new ArrayList<>();
    lines.add(status);

    FileManager.writeFile("customer.txt", lines);

    return status;
}
    

    public static String[][] getBillsByRegion(String region) {

        List<String> customers = FileManager.readFile("customers.txt");
        List<String> bills = FileManager.readFile("bills.txt");

        List<String[]> rows = new ArrayList<>();

        for (String b : bills) {
            String[] bp = b.split("\\|");
            String meterCode = bp[1];
            String amount = bp[5];
            String status = bp[6].equals("true") ? "Paid" : "Unpaid";

            for (String c : customers) {
                String[] cp = c.split("\\|");

                String customerRegion = cp[4];
                String customerMeter = cp[6];

                if (customerMeter.equals(meterCode)
                        && customerRegion.equalsIgnoreCase(region)) {

                    String customerName = cp[1] + " " + cp[2];
                    String nationalId = cp[0];

                    rows.add(new String[]{
                        customerName,
                        meterCode,
                        nationalId,
                        status,
                        amount
                    });
                }
            }
        }

        // تحويل List → 2D Array
        String[][] result = new String[rows.size()][5];
        for (int i = 0; i < rows.size(); i++) {
            result[i] = rows.get(i);
        }

        return result;
    }
}


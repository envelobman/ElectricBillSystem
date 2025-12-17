package Controller;

import Model.*;
import java.util.*;
import DataStorage.*;
public class AdminController {

    // ---------- GET ALL BILLS ----------
    public List<Bill> getAllBills() {
        return FileManager.readBills();
    }
    // ----------- help to search ---------
    public Bill getBillById(String billId) {
    for (Bill b : FileManager.readBills()) {
        if (b.getBillId().equals(billId))
            return b;
    }
    return null;
}


    // ---------- TOTAL COLLECTED ----------
    public double totalCollected() {
        double sum = 0;

        for (Bill b : FileManager.readBills()) {
            if (b.isPaid())
                sum += b.getAmount();
        }

        return sum;
    }
    public Object[] getRegionStatistics(String region) {

    int totalBills = 0;
    int paidBills = 0;
    int unpaidBills = 0;
    double totalAmount = 0;

    List<Customer> customers = FileManager.readCustomers();
    List<Bill> bills = FileManager.readBills();

    for (Customer c : customers) {

        if (!c.getAddress().equalsIgnoreCase(region))
            continue;

        for (Bill b : bills) {

            if (b.getMeterCode().equals(c.getMeterCode())) {

                totalBills++;
                totalAmount += b.getAmount();

                if (b.isPaid())
                    paidBills++;
                else
                    unpaidBills++;
            }
        }
    }

    return new Object[]{
        region,
        totalBills,
        totalAmount,
        paidBills,
        unpaidBills
    };
}

    
}
    


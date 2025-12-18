package Controller;

import Model.*;
import DataStorage.*;
import java.util.*;

public class BillController {

    private final Tariff tariff = new Tariff();

    // ---------- CREATE BILL ----------
    public Bill createBill(Customer c, double currentReading, String year, String month) {

        double consumption = c.calculateConsumption(currentReading);
        double amount = tariff.calculateAmount(consumption);

        String billId = "B" + (int)(Math.random() * 9000 + 1000);

        return new Bill(
                billId,
                c.getMeterCode(),
                year,
                month,
                currentReading,
                consumption,
                amount,
                false
        );
    }

    // ---------- SAVE BILL ----------
    public void saveBill(Bill bill) {
        List<Bill> bills = FileManager.readBills();
        bills.add(bill);
        FileManager.writeBills(bills);
    }

    // ---------- GET BILLS BY METER ----------
    public List<Bill> getBillsByMeter(String meterCode) {
        List<Bill> result = new ArrayList<>();

        for (Bill b : FileManager.readBills()) {
            if (b.toString().contains("|" + meterCode + "|"))
                result.add(b);
        }
        return result;
    }

    // ---------- PAY BILL ----------
    public boolean payBill(String billId) {
        List<Bill> bills = FileManager.readBills();

        for (Bill b : bills) {
            if (b.toString().startsWith(billId + "|")) {
                b.setPaid(true);
                FileManager.writeBills(bills);
                return true;
            }
        }
        return false;
    }

    // ---------- TOTAL DUE FOR METER ----------
    public double getTotalUnpaid(String meterCode) {
        double sum = 0;

        for (Bill b : FileManager.readBills()) {
            if (b.toString().contains("|" + meterCode + "|") && !b.isPaid())
                sum += b.getAmount();
        }
        return sum;
    }

    // ---------- GENERATE BILL TEXT ----------
    public String generateBillText(String meterCode) {
        Customer customer = null;
        Bill lastBill = null;
        for (Customer c : FileManager.readCustomers()) {
            if (c.getMeterCode().equals(meterCode)) {
                customer = c;
                break;
            }
        }
        for (Bill b : FileManager.readBills()) {
            if (b.getMeterCode().equals(meterCode)) {
                lastBill = b; 
            }
        }
        if (customer == null || lastBill == null)
            return "Meter not found";

        return
            "------- ELECTRICITY BILL -------\n" +
            "Customer Name : " + customer.getfirstName() + " " + customer.getsecondName() + "\n" +
            "Meter Code    : " + meterCode + "\n" +
            "Month         : " + lastBill.getMonth() + "\n" +
            "Amount        : " + lastBill.getAmount() + " EGP\n" +
            "Status        : " + (lastBill.isPaid() ? "PAID" : "UNPAID") + "\n" +
            "--------------------------------";
    }


        public void updateTariff(String newTariff) {


        List<String> lines = new ArrayList<>();
        lines.add(newTariff);

        FileManager.writeFile("bills.txt", lines);

    }

    
    public String generateBillAmount(String meterCode) {

    if (Validation.isEmpty(meterCode))
        return null;

    Customer c = new CustomerController().getCustomer(meterCode);
    if (c == null)
        return null;

    // آخر قراءة
    double lastReading = c.getLastReading();

    // القراءة الشهرية
    double currentReading = lastReading + 100;

    double consumption = currentReading - lastReading;

    Tariff tariff = new Tariff();
    double amount = tariff.calculateAmount(consumption);

    return String.valueOf(amount);
}
    
    
    public void payBillByMeter(String meterCode) {
    try {
        List<Bill> bills = FileManager.readBills();
        boolean updated = false;

        for (Bill b : bills) {

            if (b.getMeterCode().equals(meterCode) && !b.isPaid()) {
                b.setPaid(true); 
                b.setAmount(0.0); 
                updated = true;
                break;                  
            }
        }

        if (updated) {
            FileManager.writeBills(bills); 
        } else {
            System.out.println("No unpaid bill found for meter code: " + meterCode);
        }

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error while paying bill for meter code: " + meterCode);
    }
}
    
    public static String[][] getBillById(String billId) {

    List<String> bills = FileManager.readFile("bills.txt");
    List<String[]> rows = new ArrayList<>();

    for (String line : bills) {
        String[] p = line.split("\\|");

        if (p[0].equals(billId)) {

            String status = p[7].equalsIgnoreCase("true") ? "Paid" : "Unpaid";

            rows.add(new String[] {
                p[0],          // billId
                p[1],          // meterCode
                p[2],          // year
                p[3],          // month
                p[4],          // reading
                p[5],          // consumption
                p[6],          // amount
                status         // paid
            });

            break; // billId unique
        }
    }

  
    String[][] result = new String[rows.size()][8];
    for (int i = 0; i < rows.size(); i++) {
        result[i] = rows.get(i);
    }

    return result;
}
}

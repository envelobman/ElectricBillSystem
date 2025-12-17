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
            if (b.toString().contains("|" + meterCode + "|"))
                lastBill = b;
        }
if (customer == null || lastBill == null)
            return "Meter not found";

        return
                "------- ELECTRICITY BILL -------\n" +
        
                "Customer Name : " + customer.getfirstName() + " " + customer.getsecondName() + "\n" +
                "Meter Code    : " + meterCode + "\n" +
                "Month         : " + lastBill.toString().split("\\|")[3] + "\n" +
                "Amount        : " + lastBill.getAmount() + " EGP\n" +
                "Status        : " + (lastBill.isPaid() ? "PAID" : "UNPAID") + "\n" +
                "--------------------------------";
    }
    public String updateTariff(String newTariff) {

    if (Validation.isEmpty(newTariff))
        return "Tariff is required";

    List<String> lines = new ArrayList<>();
    lines.add(newTariff);

    FileManager.writeFile("bills.txt", lines);

    return "Tariff updated successfully";
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
}

package Controller;
import Model.*;
import DataStorage.*;
import java.util.*;

public class BillController {

    private final FileManager fm = new FileManager();
    private final String FILE = "bills.txt";
    private final Tariff tariff = new Tariff();

    public Bill createBill(Customer c, double currentReading, String month) {

        double cons = currentReading - c.getLastReading();
        double amount = tariff.calculateAmount(cons);

        String billId = "" + (int)(Math.random()*9000 + 1000);

        return new Bill(billId, c.getMeterCode(), month,
                currentReading, cons, amount, false);
    }
    public void saveBill(Bill b) {
        fm.append(FILE, b.toString());
    }

    public List<Bill> getBillsByMeter(String meter) {

        List<Bill> list = new ArrayList<>();

        for(String line : fm.readFile(FILE)) {
            String[] p = line.split("\\|");
            if(p[1].equals(meter)) {

                Bill b = new Bill(p[0], p[1], p[2], Double.parseDouble(p[3]),
                        Double.parseDouble(p[4]), Double.parseDouble(p[5]),
                        Boolean.parseBoolean(p[6]));

                list.add(b);
            }
        }
        return list;
    }

    public String payBill(String billId) {

        List<String> all = fm.readFile(FILE);

        for(int i=0; i<all.size(); i++) {
            String[] p = all.get(i).split("\\|");

            if(p[0].equals(billId)) {
                p[6] = "true";
                all.set(i, String.join("|", p));
                fm.writeFile(FILE, all);
                return "Bill paid";
            }
        }

        return "Bill not found";
    }
}
package Controller;
import Model.*;
import DataStorage.*;
import java.util.*;

public class OperatorController {

    private final FileManager fm = new FileManager();
    private final String FILE = "meters.txt";
    private Meter parse(String line) {
        String[] p = line.split("\\|");
        return new Meter(p[0], Double.parseDouble(p[1]),
                Boolean.parseBoolean(p[2]));
    }

    public String updateMeter(String meterCode, double newReading) {

        if(!Validation.isPositive(newReading))
            return "Invalid Reading";

        List<String> all = fm.readFile(FILE);

        for(int i=0; i<all.size(); i++) {
            String[] p = all.get(i).split("\\|");

            if(p[0].equals(meterCode)) {
                Meter m = parse(all.get(i));
                m.updateReading(newReading);
                all.set(i, m.toString());
                fm.writeFile(FILE, all);
                return "Meter updated";
            }
        }

        return "Meter not found";
    }


    public String deactivate(String meterCode) {
        List<String> all = fm.readFile(FILE);

        for(int i=0; i<all.size(); i++) {
            String[] p = all.get(i).split("\\|");

            if(p[0].equals(meterCode)) {
                Meter m = parse(all.get(i));
                m.deactivate();
                all.set(i, m.toString());
                fm.writeFile(FILE, all);
                return "Meter Deactivated";
            }
        }

        return "Not found";
    }


    public String activate(String meterCode) {
        List<String> all = fm.readFile(FILE);

        for(int i=0; i<all.size(); i++) {
            String[] p = all.get(i).split("\\|");

            if(p[0].equals(meterCode)) {
                Meter m = parse(all.get(i));
                m.activate();
                all.set(i, m.toString());
                fm.writeFile(FILE, all);
                return "Meter Activated";
            }
        }

        return "Not found";
    }
    public String setCustomerStatus(String customerCustomerState, String deactivate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String updateTariff(String triffSlice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String[] searchForCustomer(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String generateBill(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
       
    private String getTotalunpaidBills() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getTotalpaidBills() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getTotalAmount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getTotalBills() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}


package Controller;
import Model.*;
import java.util.*;
import DataStorage.*;

public class AdminController {

    private final FileManager fm = new FileManager();
    private final String FILE = "bills.txt";

    public List<Bill> getAllBills() {

        List<Bill> list = new ArrayList<>();

        for(String line : fm.readFile(FILE)) {
            String[] p = line.split("\\|");

            Bill b = new Bill(p[0], p[1], p[2], Double.parseDouble(p[3]),
                    Double.parseDouble(p[4]), Double.parseDouble(p[5]),
                    Boolean.parseBoolean(p[6]));

            list.add(b);
        }

        return list;
    }

    public double totalCollected() {

        double sum = 0;

        for(String line : fm.readFile(FILE)) {
            String[] p = line.split("\\|");
            if(Boolean.parseBoolean(p[6]))  // paid == true
                sum += Double.parseDouble(p[5]);
        }

        return sum;
    }
}



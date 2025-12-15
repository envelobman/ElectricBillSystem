package Controller;
import DataStorage.*;
import Model.*;
import java.util.*;

public class CustomerController {

    private final FileManager fm = new FileManager();
    private final String FILE = "customers.txt";

    private Customer parse(String line) {
        String[] p = line.split("\\|");

        String type = p[5];

        if(type.equals("old"))
            return new OldCustomer(p[0], p[1], p[2], p[3], p[4],Double.parseDouble(p[6]));

        if(type.equals("new"))
            return new NewCustomer(p[0], p[1], p[2], p[3], p[4],
                    p[6]);

        return new Customer(p[0], p[1], p[2], p[3], p[4],
                Double.parseDouble(p[6]));
    }
    public Customer getCustomer(String meter) {
        for(String line : fm.readFile(FILE)) {
            String[] p = line.split("\\|");
            if(p[4].equals(meter))
                return parse(line);
        }
        return null;
    }
    public String addCustomer(Customer c) {

        if(Validation.isEmpty(c.getfirstName())||Validation.isEmpty(c.getsecondName())) return "Name required!";
        if(!Validation.isValidEmail(c.getEmail())) return "Invalid Email!";
        if(Validation.isEmpty(c.getMeterCode())) return "Meter code missing!";

        fm.append(FILE, c.toString());
        return "Customer Added";
    }
    public String updateReading(String meterCode, double newReading) {

        if(!Validation.isPositive(newReading))
            return "Reading must be positive";

        List<String> all = fm.readFile(FILE);
        boolean found = false;

        for(int i=0; i<all.size(); i++) {
            String[] p = all.get(i).split("\\|");
            if(p[4].equals(meterCode)) {

                Customer c = parse(all.get(i));

                if(newReading < c.getLastReading())
                    return "New reading < last reading";

                c.setLastReading(newReading);
                all.set(i, c.toString());
                found = true;
                break;
            }
        }

        if(!found) return "Meter not found";

        fm.writeFile(FILE, all);
        return "Reading updated";
    }
}

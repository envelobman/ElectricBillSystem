package Controller;
import DataStorage.*;
import Model.*;

public class ComplaintController {

    private final FileManager fm = new FileManager();
    private final String FILE = "complaints.txt";

    public String addComplaint(String customerId, String text) {

        if(Validation.isEmpty(text))
            return "Complaint text is empty!";

        String id = "C" + (int)(Math.random()*9000 + 1000);

        Complaint c = new Complaint(id, customerId, text);
        fm.append(FILE, c.toString());

        return "Complaint submitted";
    }
}


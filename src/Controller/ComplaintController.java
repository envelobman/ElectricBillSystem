package Controller;

import Model.*;
import DataStorage.*;
import java.util.*;

public class ComplaintController {

    // ---------- ADD COMPLAINT ----------
    public Complaint addComplaint(String customerId, String text) {

        if (Validation.isEmpty(text))
            return null;  // أو ترجع Optional أو Exception حسب المشروع

        String id = "C" + (int)(Math.random() * 9000 + 1000);

        Complaint c = new Complaint(id, customerId, text);

        // استخدام FileManager
        List<Complaint> complaints = FileManager.readComplaints();
        complaints.add(c);
        FileManager.writeComplaints(complaints);

        return c;
    }

    // ---------- GET ALL COMPLAINTS ----------
    public List<Complaint> getAllComplaints() {
        return FileManager.readComplaints();
    }

    // ---------- GET COMPLAINTS BY CUSTOMER ----------
    public List<Complaint> getComplaintsByCustomer(String customerId) {
        List<Complaint> result = new ArrayList<>();

        for (Complaint c : FileManager.readComplaints()) {
            if (c.toString().contains("|" + customerId + "|"))
                result.add(c);
        }

        return result;
    }
    
//    public String sendComplaint(String meterCode, String complaintText) {
//
//        if (Validation.isEmpty(meterCode))
//            return "Meter code is required";
//
//        if (Validation.isEmpty(complaintText))
//            return "Complaint text is required";
//
//        String complaintId = "C" + (int)(Math.random() * 9000 + 1000);
//
//        String line = complaintId + "|" + meterCode + "|" + complaintText;
//
//        List<String> complaints = FileManager.readFile("complaints.txt");
//        complaints.add(line);
//        FileManager.writeFile("complaints.txt", complaints);
//
//        return "Complaint sent successfully";
//    }
//    
    
}

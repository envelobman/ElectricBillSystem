/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */
public class Complaint {
    private String complaintId;
    private String customerId;
    private String text;

    public Complaint(String complaintId, String customerId, String text) {
        this.complaintId = complaintId;
        this.customerId = customerId;
        this.text = text;
    }
    public String getComplaintId(){
        return complaintId;
    }
    @Override
    public String toString() {
        return complaintId + "|" + customerId + "|" + text;
    }
}

/* شكل(file)
C1|1|The bill is too high.
C2|3|Meter reading incorrect.
*/
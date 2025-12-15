/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */
public class Customer {
    protected String nationalId;
    protected String firstname;
     protected String secondname;
    protected String address;
    protected String email;
    protected String meterCode;
    protected double lastReading;
     protected String phone;

    public Customer(String nationalId, String firstname,String secondname,String phone, String address, String email,
                    String meterCode, double lastReading) {
        this.nationalId = nationalId;
        this.firstname = firstname;
        this.secondname = secondname;
        this.address = address;
        this.email = email;
        this.meterCode = meterCode;
        this.lastReading = lastReading;
        this.phone = phone;
    }

    public String getId() { return nationalId; }
     public String getphone() { return phone; }
    public String getfirstName() { return firstname; }
     public String getsecondName() { return secondname; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getMeterCode() { return meterCode; }
    public double getLastReading() { return lastReading; }

    public void setLastReading(double r) { this.lastReading = r; }

    @Override
    public String toString() {
        // format: id|firstname|secondname|phone|address|email|meterCode|type|lastReading
        return nationalId + "|" + firstname + "|" +secondname +"|"+ phone+"|"+address + "|" + email + "|" +
                meterCode + "|base|" + lastReading;
    }
    public double calculateConsumption(double currentReading) {
    return currentReading - this.lastReading;
}

public double calculateBill(double currentReading, Tariff tariff) {
    double cons = calculateConsumption(currentReading);
    return tariff.calculateAmount(cons);
}


}






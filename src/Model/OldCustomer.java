/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */
public class OldCustomer extends Customer {

    public OldCustomer(String nationalId, String firstname,String secondname,String phone, String address, String email,
                       String meterCode, double lastReading) {
        super(nationalId, firstname,secondname,phone ,address, email, meterCode, lastReading);
    }

    @Override
    public String toString() {
        return nationalId + "|" + firstname + "|" +secondname + "|"+phone +"|" + address + "|" + email + "|" +
                meterCode + "|old|" + lastReading;
    }
}

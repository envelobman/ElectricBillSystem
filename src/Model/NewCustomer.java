/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */
public class NewCustomer extends Customer {

    private String contractFile; 

    public NewCustomer(String nationalId, String firstname,String secondname,String phone ,String region, String email,String contractFile) {

        super(nationalId,firstname,secondname,phone ,region, email, "N/A" , 0);
        this.contractFile = contractFile;
    }

    public String getContractFile() { return contractFile; }

    @Override
    public String toString() {
        return nationalId + "|" + firstname + "|" + secondname+ "|"+phone +"|"+ address + "|" + email + "|" +
                meterCode + "|new|" + contractFile + "|" + lastReading;
    }
}

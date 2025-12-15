/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */

    public class Tariff {

   
    private final double slice1 = 0.5;   
    private final double slice2 = 0.75;  
    private final double slice3 = 1.25;  

    public double calculateAmount(double consumption) {
        if (consumption <= 50)
            return consumption * slice1;

        else if (consumption <= 100)
            return (50 * slice1) + ((consumption - 50) * slice2);

        else
            return (50 * slice1) + (50 * slice2) + ((consumption - 100) * slice3);
    }
}



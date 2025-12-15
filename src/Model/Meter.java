/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yousef
 */
public class Meter {
    private String meterCode;
    private double currentReading;
    private boolean active;

    public Meter(String meterCode, double currentReading, boolean active) {
        this.meterCode = meterCode;
        this.currentReading = currentReading;
        this.active = active;
    }

    public void updateReading(double reading) {
        this.currentReading = reading;
    }

    @Override
    public String toString() {
        return meterCode + "|" + currentReading + "|" + active;
    }
    public double getCurrentReading() {
    return currentReading;
}

public boolean isActive() {
    return active;
}

public void deactivate() {
    this.active = false;
}

public void activate() {
    this.active = true;
}

}

/*   شكل (file)
meterCode|currentReading|active
1022|420|true
8841|30|true
5510|210|false
*/
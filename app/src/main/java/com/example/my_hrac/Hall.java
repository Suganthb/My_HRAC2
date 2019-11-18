package com.example.my_hrac;

import androidx.annotation.NonNull;

public class Hall {
    private String Capacity;
    private  String HallNumber;

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getHallNumber() {
        return HallNumber;
    }

    public void setHallNumber(String hallNumber) {
        HallNumber = hallNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return Capacity +": " + HallNumber;
    }
}

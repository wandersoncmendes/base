package com.base.dto;

import com.base.models.*;

import java.io.Serializable;

public class DataSaveGym implements Serializable {
    private boolean updateNeighborhood;
    private boolean updateStreet;
    private boolean newAddress;
    private boolean activeGym;
    private Address address;

    public DataSaveGym() {
    }

    public boolean isUpdateNeighborhood() {
        return updateNeighborhood;
    }

    public void setUpdateNeighborhood(boolean updateNeighborhood) {
        this.updateNeighborhood = updateNeighborhood;
    }

    public boolean isUpdateStreet() {
        return updateStreet;
    }

    public void setUpdateStreet(boolean updateStreet) {
        this.updateStreet = updateStreet;
    }

    public boolean isNewAddress() {
        return newAddress;
    }

    public void setNewAddress(boolean newAddress) {
        this.newAddress = newAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActiveGym() {
        return activeGym;
    }

    public void setActiveGym(boolean activeGym) {
        this.activeGym = activeGym;
    }
}

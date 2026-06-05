package com.warehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accessories")
@DiscriminatorValue("ACCESSORY")
public class Accessory extends Product {

    @Column(name = "connectorType")
    private String connectorType;

    @Column(name = "portCount")
    private int portCount;

    @Column(name = "compatibleWith")
    private String compatibleWith;

    public Accessory() {}

    public Accessory(String productId, String name, String brand, double price, int quantity, int warrantyYears, String connectorType, int portCount, String compatibleWith) {
        super(productId, name, brand, price, quantity, warrantyYears);
        this.connectorType = connectorType;
        this.portCount = portCount;
        this.compatibleWith = compatibleWith;
    }

    // Getters
    public String getConnectorType() {
        return connectorType;
    }

    public int getPortCount() {
        return portCount;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    // Setters
    public void setConnectorType(String connectorType)  {
        this.connectorType  = connectorType;
    }

    public void setPortCount(int portCount) {
        this.portCount = portCount;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nConnector: %s | Ports: %d | Compatible: %s", connectorType, portCount, compatibleWith);
    }
}

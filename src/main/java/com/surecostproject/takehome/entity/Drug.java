/* Original code before refactoring


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Drug {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "uid", updatable = false, nullable = false)
    private UUID uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // Constructors
    public Drug() {}

    public Drug(String name, String manufacturerName, int quantity, BigDecimal price) {
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
*/

/* chatGPT refactored code after initial implementation, 
it reccomended Lombok annotations to reduce boilerplate code */

package com.surecostproject.takehome.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "uid", updatable = false, nullable = false)
    private UUID uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}

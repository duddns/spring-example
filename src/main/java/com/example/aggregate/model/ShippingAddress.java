package com.example.aggregate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "shipping_addresses")
public class ShippingAddress extends BaseEntity {

    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "address")
    private String address;
    
    @Getter(value = AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    
    
    @Builder
    public ShippingAddress(String zipCode, String address, Order order) {
        Assert.notNull(zipCode, "zipCode must not be null");
        Assert.notNull(address, "address must not be null");
        //Assert.notNull(order, "order must not be null");
        
        this.zipCode = zipCode;
        this.address = address;
        this.order = order;
    }
}

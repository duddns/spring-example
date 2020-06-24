package com.example.aggregate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "aggregate_shipping_addresses")
public class ShippingAddress extends BaseEntity {
    
    @Getter
    @Column(name = "zip_code")
    private String zipCode;
    
    @Getter
    @Column(name = "address")
    private String address;
    
    @Setter(value = AccessLevel.PACKAGE)
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    
    
    @Builder
    public ShippingAddress(String zipCode, String address) {
        Assert.notNull(zipCode, "zipCode must not be null");
        Assert.notNull(address, "address must not be null");
        
        this.zipCode = zipCode;
        this.address = address;
    }
}

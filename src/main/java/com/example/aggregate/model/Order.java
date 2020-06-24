package com.example.aggregate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "aggregate_orders")
public class Order extends BaseEntity {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;
    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShippingAddress shippingAddress;
    
    
    @Builder(builderClassName = "OrderBuilder")
    public Order(@Singular(value = "lineItem") List<LineItem> lineItems,
            ShippingAddress shippingAddress) {
        Assert.notEmpty(lineItems, "lineItems must not be empty");
        Assert.notNull(shippingAddress, "shippingAddress must not be null");
        
        if (null == this.lineItems) {
            this.lineItems = new ArrayList<>();
        }
        for (LineItem lineItem : lineItems) {
            addLineItem(lineItem);
        }
        
        setShippingAddress(shippingAddress);
    }
    
    
    // 연관관계 편의 메서드
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
        
        lineItem.setOrder(this);
    }
    
    // 연관관계 편의 메서드
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        
        shippingAddress.setOrder(this);
    }
}

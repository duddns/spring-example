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
@Entity(name = "line_items")
public class LineItem extends BaseEntity {

    @Column(name = "product_id")
    private String productId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private Long price;
    
    @Getter(value = AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    
    
    @Builder
    public LineItem(String productId, String name, Long price) {
        Assert.notNull(productId, "productId must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");
        
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}

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
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "aggregate_line_items")
public class LineItem extends BaseEntity {

    @Getter
    @Column(name = "product_id")
    private String productId;
    
    @Getter
    @Column(name = "name")
    private String name;
    
    @Getter
    @Column(name = "price")
    private Long price;
    
    @Setter(value = AccessLevel.PACKAGE)
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

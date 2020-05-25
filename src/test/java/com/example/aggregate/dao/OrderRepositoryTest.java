package com.example.aggregate.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.aggregate.model.LineItem;
import com.example.aggregate.model.Order;
import com.example.aggregate.model.ShippingAddress;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "local")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    
    
    @Test
    public void test_SaveAndLoad() {
        // aggregate root Order
        // builder 패턴
        Order order = Order.builder()
                .lineItem(LineItem.builder()
                        .productId("product-1")
                        .name("sample product 1")
                        .price(10000L)
                        .build())
                .lineItem(LineItem.builder()
                        .productId("product-3")
                        .name("sample product 3")
                        .price(30000L)
                        .build())
                .shippingAddress(ShippingAddress.builder()
                        .zipCode("12345")
                        .address("서울시 강남구 역삼동")
                        .build())
                .build();
        
        // aggregate root 저장
        orderRepository.save(order);
        
        List<Order> list = orderRepository.findAll();
        
        assertThat(list.size()).isEqualTo(1);
        
        Order item = list.get(0);
        
        assertThat(item.getLineItems().size()).isEqualTo(2);
        assertThat(item.getLineItems().get(0).getProductId()).isEqualTo("product-1");
        assertThat(item.getShippingAddress().getZipCode()).isEqualTo("12345");
        
        // 자식 Item 삭제
        // orphanRemoval = true (참조하는 부모가 없는 고아 객체는 삭제) 이면, LineItem 을 db 에서 delete
        item.getLineItems().clear();
        
        // product 1 product 3 delete 쿼리 강제 실행
        // 여기서 flush 안하면 product 2 insert 쿼리 실행 시점에 delete 쿼리 실행됨
        //orderRepository.flush();
        
        item.addLineItem(LineItem.builder()
                .productId("product-2")
                .name("sample product 2")
                .price(20000L)
                .build());
        
        orderRepository.save(item);
        
        // product 2 insert 쿼리 강제 실행
        orderRepository.flush();
        
        list = orderRepository.findAll();
        
        assertThat(list.size()).isEqualTo(1);
        
        item = list.get(0);
        
        assertThat(item.getLineItems().size()).isEqualTo(1);
        assertThat(item.getLineItems().get(0).getProductId()).isEqualTo("product-2");
    }
}

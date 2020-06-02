package com.example.lombok.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@ActiveProfiles(value = "unit-test")
public class LombokEqualsTests {

    @Test
    public void test_cat_id_null() {
        Cat cat1 = null;
        Cat cat2 = null;
        
        cat1 = Cat.builder()
                .build();
        cat2 = Cat.builder()
                .build();
        
        assertThat(cat1.getId()).isEqualTo(null);
        assertThat(cat2.getId()).isEqualTo(null);
        assertThat(cat1.equals(cat2)).isFalse();
    }
    
    @Test
    public void test_cat_id_다른경우() {
        Cat cat1 = null;
        Cat cat2 = null;
        
        cat1 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat1, "id", 1L);
        cat2 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat2, "id", 2L);
        
        assertThat(cat1.getId()).isEqualTo(1L);
        assertThat(cat2.getId()).isEqualTo(2L);
        assertThat(cat1.equals(cat2)).isFalse();
        
        cat1 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat1, "id", 1L);
        cat2 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat2, "id", 1L);
        
        assertThat(cat1.getId()).isEqualTo(1L);
        assertThat(cat2.getId()).isEqualTo(1L);
        assertThat(cat1.equals(cat2)).isTrue();
    }
    
    @Test
    public void test_cat_id_같은경우() {
        Cat cat1 = null;
        Cat cat2 = null;
        
        cat1 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat1, "id", 1L);
        cat2 = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat2, "id", 1L);
        
        assertThat(cat1.getId()).isEqualTo(1L);
        assertThat(cat2.getId()).isEqualTo(1L);
        assertThat(cat1.equals(cat2)).isTrue();
    }
    
    @Test
    public void test_cat_이름_다른경우() {
        Cat cat1 = null;
        Cat cat2 = null;
        
        cat1 = Cat.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(cat1, "id", 1L);
        cat2 = Cat.builder()
                .name("고길동")
                .build();
        ReflectionTestUtils.setField(cat2, "id", 1L);
        
        assertThat(cat1.getId()).isEqualTo(1L);
        assertThat(cat2.getId()).isEqualTo(1L);
        assertThat(cat1.equals(cat2)).isFalse();
    }
    
    @Test
    public void test_cat_이름_같은경우() {
        Cat cat1 = null;
        Cat cat2 = null;
        
        cat1 = Cat.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(cat1, "id", 1L);
        cat2 = Cat.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(cat2, "id", 1L);
        
        assertThat(cat1.getId()).isEqualTo(1L);
        assertThat(cat2.getId()).isEqualTo(1L);
        assertThat(cat1.equals(cat2)).isTrue();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_catdog_id_null() {
        Cat cat = null;
        Dog dog = null;
        
        cat = Cat.builder()
                .build();
        dog = Dog.builder()
                .build();
        
        assertThat(cat.getId()).isEqualTo(null);
        assertThat(dog.getId()).isEqualTo(null);
        assertThat(cat.equals(dog)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_catdog_id_다른경우() {
        Cat cat = null;
        Dog dog = null;
        
        cat = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat, "id", 1L);
        dog = Dog.builder()
                .build();
        ReflectionTestUtils.setField(dog, "id", 2L);
        
        assertThat(cat.getId()).isEqualTo(1L);
        assertThat(dog.getId()).isEqualTo(2L);
        assertThat(cat.equals(dog)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_catdog_id_같은경우() {
        Cat cat = null;
        Dog dog = null;
        
        cat = Cat.builder()
                .build();
        ReflectionTestUtils.setField(cat, "id", 1L);
        dog = Dog.builder()
                .build();
        ReflectionTestUtils.setField(dog, "id", 1L);
        
        assertThat(cat.getId()).isEqualTo(1L);
        assertThat(dog.getId()).isEqualTo(1L);
        assertThat(cat.equals(dog)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_catdog_name_같은경우() {
        Cat cat = null;
        Dog dog = null;
        
        cat = Cat.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(cat, "id", 1L);
        dog = Dog.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(dog, "id", 1L);
        
        assertThat(cat.getId()).isEqualTo(1L);
        assertThat(dog.getId()).isEqualTo(1L);
        assertThat(cat.equals(dog)).isFalse();
    }
}

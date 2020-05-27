package com.example.lombok.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@ActiveProfiles(value = "unit-test")
public class LombokEqualsTests {

    @Test
    public void test_Person_id_null() {
        Person person1 = null;
        Person person2 = null;
        
        person1 = Person.builder()
                .build();
        person2 = Person.builder()
                .build();
        
        assertThat(person1.getId()).isEqualTo(null);
        assertThat(person2.getId()).isEqualTo(null);
        assertThat(person1.equals(person2)).isFalse();
    }
    
    @Test
    public void test_Person_id_다른경우() {
        Person person1 = null;
        Person person2 = null;
        
        person1 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person1, "id", 1L);
        person2 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person2, "id", 2L);
        
        assertThat(person1.getId()).isEqualTo(1L);
        assertThat(person2.getId()).isEqualTo(2L);
        assertThat(person1.equals(person2)).isFalse();
        
        person1 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person1, "id", 1L);
        person2 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person2, "id", 1L);
        
        assertThat(person1.getId()).isEqualTo(1L);
        assertThat(person2.getId()).isEqualTo(1L);
        assertThat(person1.equals(person2)).isTrue();
    }
    
    @Test
    public void test_Person_id_같은경우() {
        Person person1 = null;
        Person person2 = null;
        
        person1 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person1, "id", 1L);
        person2 = Person.builder()
                .build();
        ReflectionTestUtils.setField(person2, "id", 1L);
        
        assertThat(person1.getId()).isEqualTo(1L);
        assertThat(person2.getId()).isEqualTo(1L);
        assertThat(person1.equals(person2)).isTrue();
    }
    
    @Test
    public void test_Person_이름_다른경우() {
        Person person1 = null;
        Person person2 = null;
        
        person1 = Person.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(person1, "id", 1L);
        person2 = Person.builder()
                .name("고길동")
                .build();
        ReflectionTestUtils.setField(person2, "id", 1L);
        
        assertThat(person1.getId()).isEqualTo(1L);
        assertThat(person2.getId()).isEqualTo(1L);
        assertThat(person1.equals(person2)).isFalse();
    }
    
    @Test
    public void test_Person_이름_같은경우() {
        Person person1 = null;
        Person person2 = null;
        
        person1 = Person.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(person1, "id", 1L);
        person2 = Person.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(person2, "id", 1L);
        
        assertThat(person1.getId()).isEqualTo(1L);
        assertThat(person2.getId()).isEqualTo(1L);
        assertThat(person1.equals(person2)).isTrue();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_PersonHuman_id_null() {
        Person person = null;
        Human human = null;
        
        person = Person.builder()
                .build();
        human = Human.builder()
                .build();
        
        assertThat(person.getId()).isEqualTo(null);
        assertThat(human.getId()).isEqualTo(null);
        assertThat(person.equals(human)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_PersonHuman_id_다른경우() {
        Person person = null;
        Human human = null;
        
        person = Person.builder()
                .build();
        ReflectionTestUtils.setField(person, "id", 1L);
        human = Human.builder()
                .build();
        ReflectionTestUtils.setField(human, "id", 2L);
        
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(human.getId()).isEqualTo(2L);
        assertThat(person.equals(human)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_PersonHuman_id_같은경우() {
        Person person = null;
        Human human = null;
        
        person = Person.builder()
                .build();
        ReflectionTestUtils.setField(person, "id", 1L);
        human = Human.builder()
                .build();
        ReflectionTestUtils.setField(human, "id", 1L);
        
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(human.getId()).isEqualTo(1L);
        assertThat(person.equals(human)).isFalse();
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_PersonHuman_name_같은경우() {
        Person person = null;
        Human human = null;
        
        person = Person.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(person, "id", 1L);
        human = Human.builder()
                .name("홍길동")
                .build();
        ReflectionTestUtils.setField(human, "id", 1L);
        
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(human.getId()).isEqualTo(1L);
        assertThat(person.equals(human)).isFalse();
    }
}

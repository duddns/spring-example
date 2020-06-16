package com.example.lombok.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "lombok_humans")
public class Dog extends BaseEntity {

    @NonNull
    @Column(name = "name")
    private String name;
    
    
    @Builder
    public Dog(String name) {
        this.name = name;
    }
}

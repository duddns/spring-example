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
@Entity(name = "humans")
public class Human extends BaseEntity {

    @NonNull
    @Column(name = "name")
    private String name;
    
    
    @Builder
    public Human(String name) {
        this.name = name;
    }
}

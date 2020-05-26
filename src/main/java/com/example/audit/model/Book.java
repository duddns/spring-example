package com.example.audit.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "books")
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;
    
    
    @Builder
    public Book(String title) {
        this.title = title;
    }
    
    
    public void updateTitle(String title) {
        this.title = title;
    }
}

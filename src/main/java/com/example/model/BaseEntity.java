package com.example.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;
    
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        
        if (! (o instanceof BaseEntity)) {
            return false;
        }
        
        BaseEntity other = (BaseEntity) o;
        
        final Object this$id = this.id;
        final Object other$id = other.getId();
        
        if (null == this$id || null == other$id) {
            return false;
        }
        if (!this$id.equals(other$id)) {
            return false;
        }
        
        return true;
    }
}

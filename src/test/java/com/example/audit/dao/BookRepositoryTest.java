package com.example.audit.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.audit.model.Book;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "unit-test")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    
    @Test
    public void test_SaveAndLoad() {
        Long bookId = create();
        
        update(bookId);
        
        //
        AuditReader reader = AuditReaderFactory.get(entityManager);
        
        List<Number> revisions = reader.getRevisions(Book.class, bookId);
        assertThat(revisions.size()).isEqualTo(2);
        
        Book bookRev1 = reader.find(Book.class, bookId, revisions.get(0));
        assertThat(bookRev1.getTitle()).isEqualTo("이름1");
        
        Book bookRev2 = reader.find(Book.class, bookId, revisions.get(1));
        assertThat(bookRev2.getTitle()).isEqualTo("이름2");
    }
    
    private Long create() {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
        
        Book book = bookRepository.save(Book.builder()
                .title("이름1")
                .build());
        
        transactionManager.commit(transactionStatus);
        
        return book.getId();
    }
    
    private Long update(Long bookId) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
        
        Book book = bookRepository.findById(bookId).get();
        
        book.updateTitle("이름2");
        
        book = bookRepository.save(book);
        
        transactionManager.commit(transactionStatus);
        
        return book.getId();
    }
}

package com.example;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.engine.Mode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "unit-test")
public class H2DataSourceTest {

    @Autowired
    private DataSource dataSource;
    
    
    @Test
    public void test_SaveAndLoad() throws SQLException {
        Connection connection = dataSource.getConnection();
        
        org.h2.jdbc.JdbcConnection h2Conn = connection.unwrap(org.h2.jdbc.JdbcConnection.class);
        
        DatabaseMetaData metadata = h2Conn.getMetaData();
        
        String url = metadata.getURL();
        assertThat(url).isEqualTo("jdbc:h2:mem:testdb");
        
        org.h2.jdbc.JdbcConnection.Settings settings = h2Conn.getSettings();
        assertThat(settings.mode.getEnum()).isEqualTo(Mode.ModeEnum.MySQL);
    }
}

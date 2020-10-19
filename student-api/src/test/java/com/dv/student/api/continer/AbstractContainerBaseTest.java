package com.dv.student.api.continer;


import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractContainerBaseTest {

    protected static final PostgreSQLContainer postgres;

    static {
        postgres = new PostgreSQLContainer("postgres:11.1")
                .withDatabaseName("studentapp")
                .withUsername("postgres")
                .withPassword("example");
        //Mapped port can only be obtained after container is started.
        postgres.start();

        try{
            Connection connection = getConnection(postgres);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS students");
            statement.execute("CREATE TABLE students\n" +
                    "(\n" +
                    "   id BIGSERIAL PRIMARY KEY,\n" +
                    "   name VARCHAR (255) NOT NULL\n" +
                    ")");
            statement.execute("CREATE UNIQUE INDEX email_idx ON students(name)");

            statement.execute("INSERT INTO students (id, name) VALUES(1, 'Deepak')");
            statement.execute("INSERT INTO students (id, name) VALUES(2, 'Deep')");
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static Connection getConnection(
            PostgreSQLContainer<?> postgresContainer)
            throws SQLException {

        return DriverManager.getConnection(postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword());
    }
}

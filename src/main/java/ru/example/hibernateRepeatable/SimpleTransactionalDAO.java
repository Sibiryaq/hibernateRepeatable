package ru.example.hibernateRepeatable;

import java.sql.Connection;
import java.sql.SQLException;

public class SimpleTransactionalDAO {

    public void doInTransaction() throws SQLException {
        Connection connection = dataSource.getConnection();

        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        try (connection) {
            connection.setAutoCommit(false);

            // выполнение SQL-запросов для перевода средств
            // со счёта Софии на счёт Тимофея

            connection.commit();
        }
    }
}

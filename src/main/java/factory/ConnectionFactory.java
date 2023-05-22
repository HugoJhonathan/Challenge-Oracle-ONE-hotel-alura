package factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exceptions.MinhaException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactory {

    public static String DB_JDBC_URL;
    public static String DB_USERNAME;
    public static String DB_PASSWORD;
    private static HikariDataSource DATASOURCE;

    static {
        try (InputStream output = ConnectionFactory.class.getResourceAsStream("/application.properties")) {
            Properties prop = new Properties();
            prop.load(output);
            DB_JDBC_URL = prop.getProperty("db.url");
            DB_USERNAME = prop.getProperty("db.user");
            DB_PASSWORD = prop.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_JDBC_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.setMaximumPoolSize(20);
        DATASOURCE = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return DATASOURCE.getConnection();
        } catch (Exception e) {
            throw new MinhaException(e.getMessage());
        }
    }
}

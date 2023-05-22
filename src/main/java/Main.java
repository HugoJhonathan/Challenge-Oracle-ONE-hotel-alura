import com.formdev.flatlaf.FlatIntelliJLaf;
import exceptions.ExceptionHandler;
import factory.ConnectionFactory;
import org.flywaydb.core.Flyway;
import views.Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        Flyway flyway = Flyway
                .configure()
                .dataSource(ConnectionFactory.DB_JDBC_URL, ConnectionFactory.DB_USERNAME, ConnectionFactory.DB_PASSWORD)
                .load();
        flyway.migrate();

        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));

    }
}

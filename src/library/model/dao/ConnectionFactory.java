package library.model.dao;

import library.model.domain.Role;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;
    private static final Properties prop = new Properties();
    private static final String propFileName = "resources/db.properties";

    private ConnectionFactory() {}

    static {
        // Does not work if generating a jar file
        try (InputStream input = new FileInputStream(propFileName)) {
            prop.load(input);

            // get the property value and print it out
            String connection_url = prop.getProperty("CONNECTION_URL");
            String user = prop.getProperty("LOGIN_USER");
            String pass = prop.getProperty("LOGIN_PASS");
            input.close();
            connection = DriverManager.getConnection(connection_url, user, pass);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void changeRole(Role role) throws SQLException {
        connection.close();

        try (InputStream input = new FileInputStream(propFileName)) {
            prop.load(input);

            // get the property value and print it out
            String connection_url = prop.getProperty("CONNECTION_URL");
            String user = prop.getProperty(role.name() + "_USER");
            String pass = prop.getProperty(role.name() + "_PASS");
            input.close();
            connection = DriverManager.getConnection(connection_url, user, pass);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

package DBAppsIntroduction.Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Password: ");
//        scanner.nextLine();
        //Connect to DB

        Properties credentials = new Properties();
        credentials.setProperty("user", "root");
        credentials.setProperty("password", "Filipov5599");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/soft_uni", credentials);

        String userInput = scanner.nextLine();
        String notPrepared = String.format("SELECT * FROM employees where salary > %s", userInput);

        System.out.println(notPrepared);


        //Execute the Query
        PreparedStatement preparedStatement =
        connection.prepareStatement(
                "SELECT * FROM employees where salary > ? LIMIT 10");

        preparedStatement.setDouble(1, 18300.0);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Print Result
        while (resultSet.next()) {
            long id = resultSet.getLong("employee_id");
            String first_name = resultSet.getString("first_name");
            double salary = resultSet.getDouble("salary");
            Timestamp hire_date = resultSet.getTimestamp("hire_date");

            System.out.printf("%d - %s %.2f %s\n", id, first_name, salary, hire_date);
        }
    }
}

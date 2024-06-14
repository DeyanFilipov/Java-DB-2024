package DBAppsIntroduction.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Homework {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String MINIONS_TABLE_NAME = "minions_db";

    private Connection connection;
    private BufferedReader reader;

    public void setConnection(String username, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(CONNECTION_STRING + MINIONS_TABLE_NAME, properties);
    }

    public void getVillainsNameEx2() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) as 'count' from villains as v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count > 15\n" +
                "ORDER BY `count` DESC;";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d\n", resultSet.getString("name"), resultSet.getInt("count"));
        }
    }

    public void getMinionNamesEx3() throws IOException, SQLException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter villain id:");
        int villainId = Integer.parseInt(reader.readLine());

        String villainName = getEntityNameById(villainId, "villains");
        if (villainName == null) {
            System.out.printf("No villain with ID %d exists in the database.\n", villainId);
            return;
        }
        System.out.printf("Villain: %s\n", villainName);

        String query = "SELECT m.name, m.age from minions as m\n" +
                "JOIN minions_villains as mv on m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        ResultSet resultSet = statement.executeQuery();

        int count = 1;
        while (resultSet.next()) {
            System.out.printf("%d. %s %d\n", count++, resultSet.getString("name"), resultSet.getInt("age"));
        }
    }

    private String getEntityNameById(int entityId, String tableName) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id = ?", tableName);

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, entityId);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }

    public void addMinionEx4() throws IOException, SQLException {
        System.out.println("Enter minions info: name, age, town_name");
        reader = new BufferedReader(new InputStreamReader(System.in));
        String[] minionInfo = reader.readLine().split("\\s+");
        String minionName = minionInfo[0];
        int minionAge = Integer.parseInt(minionInfo[1]);
        String townName = minionInfo[2];

        int townId = getEntityIdByName(townName, "towns");
        if (townId == -1) {
            insertEntityInTowns(townName);
            System.out.printf("Town %s was added to the database.\n", townName);
        }

        int minionId = getEntityIdByName(minionName, "minions");
        if (minionId == -1) {
            insertMinionInMinions(minionName, minionAge, getEntityIdByName(townName, "towns"));
        }

        System.out.println("Enter villain name:");
        String villainName = reader.readLine();

        int villainId = getEntityIdByName(villainName, "villains");
        if (villainId == -1) {
            insertEntityInVillains(villainName);
            System.out.printf("Villain %s was added to the database.\n", villainName);
        }

        String query = "INSERT INTO minions_villains(minion_id, villain_id) value (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getEntityIdByName(minionName, "minions"));
        statement.setInt(2, getEntityIdByName(villainName, "villains"));
        statement.execute();
        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);
    }

    private void insertEntityInVillains(String villainName) throws SQLException {
        String query = "INSERT INTO villains(name) value(?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, villainName);
        statement.execute();
    }

    private void insertMinionInMinions(String minionName, int minionAge, int townId) throws SQLException {
        String query = "INSERT INTO minions(name , age , town_id) value(? , ? , ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);
        statement.execute();
    }

    private int getEntityIdByName(String name, String tableName) throws SQLException {
        String query = String.format("SELECT id FROM %s WHERE name = ?", tableName);

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        return rs.next() ? rs.getInt("id") : -1;
    }

    private void insertEntityInTowns(String townName) throws SQLException {
        String query = "INSERT INTO towns(name) values(?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, townName);
        statement.execute();
    }

    public void changeTownNameCastingEx5() throws IOException, SQLException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter country name:");
        String countryName = reader.readLine();
        String query = "UPDATE towns SET name = UPPER(name) WHERE country = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, countryName);
        int townsAffected = statement.executeUpdate();
        if (townsAffected == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.\n", townsAffected);
            String queryForUpdateCities = "SELECT name FROM towns WHERE country = ?";
            statement = connection.prepareStatement(queryForUpdateCities);
            statement.setString(1, countryName);
            ResultSet resultSet = statement.executeQuery();

            List<String> cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(resultSet.getString("name"));
            }
            System.out.println(cities);
        }
    }

    public void increaseAgeWithStoreProcedureEx9() throws IOException, SQLException {
        System.out.println("Enter minion id:");
        reader = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.parseInt(reader.readLine());

        String query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, minionId);
        callableStatement.execute();
    }
}

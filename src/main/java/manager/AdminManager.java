package manager;

import db.DBConnectionProvider;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManager {
    private Connection connection;

    public AdminManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public Admin getEmailPassword(String email, String password) {
        String query = " SELECT * FROM `admin` WHERE `email` =?  AND `password` =?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getEmailPassword(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Admin getEmailPassword(ResultSet resultSet) throws SQLException {
        return Admin.builder()
                .id(resultSet.getInt(1))
                .email(resultSet.getString(2))
                .password(resultSet.getString(3))
                .build();
    }

}

package manager;

import db.DBConnectionProvider;
import model.Master;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterManager {

    private Connection connection;

    public MasterManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void add(Master master) {
        String query = "INSERT INTO maters(name, surname, img_path,profession) Value(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, master.getName());
            preparedStatement.setString(2, master.getSurname());
            preparedStatement.setString(3, master.getImgPath());
            preparedStatement.setString(4, master.getProfession());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Master> getAllMaster() {
        String query = "SELECT * FROM master";
        List<Master> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Master master = getAllMasterFromResult(resultSet);
                result.add(master);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Master getMasterById(int id) {
        String query = "SELECT * FROM masters where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAllMasterFromResult(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Master getAllMasterFromResult(ResultSet resultSet) throws SQLException {
        return Master.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .surname(resultSet.getString(3))
                .imgPath(resultSet.getString(4))
                .profession(resultSet.getString(5))
                .build();
    }
}

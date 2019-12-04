package manager;

import db.DBConnectionProvider;
import model.Appointement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointementManager {
    private Connection connection;

    private ServiceManager serviceManager;
    private MasterManager masterManager;

    public AppointementManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
        serviceManager = new ServiceManager();
        masterManager = new MasterManager();
    }

    public void add(Appointement appointement) {
        String query = "INSERT INTO appointements(`name`,`time`,`service_id`,`master_id`, `phone_number`,`email`) VALUES (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, appointement.getName());
            preparedStatement.setString(2, appointement.getTime());
            preparedStatement.setInt(3, appointement.getService().getId());
            preparedStatement.setInt(4, appointement.getMaster().getId());
            preparedStatement.setString(5, appointement.getPhoneNumber());
            preparedStatement.setString(6, appointement.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  List<Appointement> getAllAppointement() {
        String query = "SELECT * FROM appointement";
        List<Appointement> result = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Appointement appointement = getAppointementFromResultSet (resultSet);
                result.add(appointement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Appointement getAppointementFromResultSet(ResultSet resultSet) throws SQLException {
        return Appointement.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .time(resultSet.getString(3))
                .service(serviceManager.getServiceById(resultSet.getInt(4)))
                .master(masterManager.getMasterById(resultSet.getInt(5)))
                .phoneNumber(resultSet.getString(6))
                .email(resultSet.getString(7))
                .build();
    }

}

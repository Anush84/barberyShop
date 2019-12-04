package manager;

import db.DBConnectionProvider;
import model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private Connection connection;
    public ServiceManager(){
        connection = DBConnectionProvider.getInstance().getConnection();
    }
    public void add (Service service){
        String query ="INSERT INTO services(name, description, ing_path, price)VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2,service.getDescription());
            preparedStatement.setString(3,service.getImgPath());
            preparedStatement.setDouble(4,service.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public List<Service> getAllService(){
        String query = "SELECT * FROM service ";
        List<Service> result = new ArrayList<>();
        Statement statement= null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Service service = getAllServiceFromResult(resultSet);
                result.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public Service getServiceById (int id){
        String query ="SELECT * FROM service where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet =statement.executeQuery(query);
            if(resultSet.next()){
                return getAllServiceFromResult(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Service getAllServiceFromResult(ResultSet resultSet) throws SQLException {
        return Service.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .description(resultSet.getString(3))
                .imgPath(resultSet.getString(4))
                .price(resultSet.getDouble(5))
                .build();
    }

}

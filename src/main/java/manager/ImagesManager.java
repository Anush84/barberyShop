package manager;


import db.DBConnectionProvider;
import model.Images;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ImagesManager {
    private Connection connection;

    public ImagesManager(){
        connection= DBConnectionProvider.getInstance().getConnection();

    }
    public void add (Images images) {
        String query = "INSERT INTO images(img_path)VALUES(?)";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1, images.getImgPath());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Images> getAllImages (){
        String query ="SELECT * FROM images";
        List<Images> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(query);
            while (resultSet.next()){
                Images images = getMasterFromResultSet(resultSet);
                result.add(images);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return  result;
    }

    private Images getMasterFromResultSet(ResultSet resultSet) throws SQLException {
        return Images.builder()
                .imgPath(resultSet.getString(1))
                .build();
    }

}

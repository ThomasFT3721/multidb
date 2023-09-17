package fr.codelines.multidb.repository;


import fr.codelines.multidb.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserRepository extends AbstractBaseRepository<UserEntity, Long> {

    @Override
    public UserEntity findById(Long id) throws SQLException {

        Map<Integer, Object> parameters = Map.of(1, id);

        ResultSet resultSet = this.executeQuery("SELECT * FROM user WHERE idUser = ?", parameters);

        UserEntity userEntity = null;
        if (resultSet.next()) {
            try {
                userEntity = this.mapResultSetToObject(resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return userEntity;
    }

}

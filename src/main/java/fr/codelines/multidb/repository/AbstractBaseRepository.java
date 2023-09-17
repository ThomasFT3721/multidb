package fr.codelines.multidb.repository;

import fr.codelines.multidb.annotation.Column;
import jakarta.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractBaseRepository<T, ID> implements BaseRepositoryInterface<T, ID> {

    protected Connection connexion;

    public boolean initializeConnection(String databaseName) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + databaseName;
        String utilisateur = "root";
        String motDePasse = "root";

        try {
            // Chargez le pilote JDBC pour MySQL (assurez-vous d'avoir le pilote MySQL JDBC dans votre projet)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établissez la connexion à la base de données
            this.connexion = DriverManager.getConnection(jdbcUrl, utilisateur, motDePasse);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Pilote JDBC introuvable.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible de se connecter à la base de données.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean closeConnection() {
        try {
            this.connexion.close();
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible de fermer la connexion à la base de données.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected ResultSet executeQuery(String query, @Nullable Map<Integer, Object> parameters) throws SQLException {
        java.sql.PreparedStatement statement = this.connexion.prepareStatement(query);

        if (parameters != null) {
            for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                statement.setObject(entry.getKey(), entry.getValue());
            }
        }

        return statement.executeQuery();
    }

    protected T executeQueryToObject(String query, Map<Integer, Object> parameters) throws SQLException {
        ResultSet resultSet = this.executeQuery(query, parameters);

        T entity = null;
        if (resultSet.next()) {
            //entity = this.mapResultSetToObject(resultSet);
        }

        return entity;
    }

    public T mapResultSetToObject(ResultSet resultSet) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, SQLException {
        //Create an instance of the entity
        //Set the fields of the entity with the values of the resultSet

        //Class of the entity
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        T entity = entityClass.getDeclaredConstructor().newInstance();

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (field.getAnnotation(Column.class) != null) {
                fieldName = field.getAnnotation(Column.class).name();
            }
            Class<?> fieldType = field.getType();
            field.set(entity, resultSet.getObject(fieldName, fieldType));
        }

        return entity;
    }

}

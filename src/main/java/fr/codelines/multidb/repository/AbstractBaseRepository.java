package fr.codelines.multidb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

            // Vous pouvez maintenant utiliser la connexion pour interagir avec la base de données

            // N'oubliez pas de fermer la connexion lorsque vous avez terminé
            connexion.close();
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
}

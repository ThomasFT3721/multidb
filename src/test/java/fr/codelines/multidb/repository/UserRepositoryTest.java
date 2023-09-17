package fr.codelines.multidb.repository;

import fr.codelines.multidb.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserRepositoryTest {

    @Test
    public void testA() {
        UserRepository userRepository = new UserRepository();
        boolean connexionSuccessfully = userRepository.initializeConnection("INDELINE_BDD1");

        if (connexionSuccessfully) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Connexion à la base de données échouée !");
        }
        userRepository.closeConnection();
    }

    @Test
    public void testB() throws SQLException {
        UserRepository userRepository = new UserRepository();
        boolean connexionSuccessfully = userRepository.initializeConnection("INDELINE_BDD1");

        if (connexionSuccessfully) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Connexion à la base de données échouée !");
        }

        UserEntity user = userRepository.findById(1L);

        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());


        userRepository.closeConnection();
    }
}

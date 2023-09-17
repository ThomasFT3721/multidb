package fr.codelines.multidb.repository;

import org.junit.jupiter.api.Test;

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
}

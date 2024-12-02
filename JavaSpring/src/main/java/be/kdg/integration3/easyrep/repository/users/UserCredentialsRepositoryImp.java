package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymStaff;
import be.kdg.integration3.easyrep.model.UserCredentials;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCredentialsRepositoryImp implements UserCredentialsRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserCredentialsRepositoryImp.class);

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public void save(UserCredentials userCredentials) {
        logger.info("Saving user credentials: {}", userCredentials);
        em.persist(userCredentials);
    }

    @Transactional
    @Override
    public void deleteUserCredentials(UserCredentials userCredentials) {
        logger.info("Attempting to delete user with ID: {}", userCredentials.getUserId());

        UserCredentials user = em.find(UserCredentials.class, userCredentials.getUserId());
        if (user != null) {
            em.remove(user); // Remove the managed entity
            logger.info("User with ID {} successfully deleted.", userCredentials.getUserId());
        } else {
            logger.warn("User with ID {} not found, cannot delete.", userCredentials.getUserId());
        }
    }



    @Override
    public UserCredentials findUserById(int id){
        return em.find(UserCredentials.class, id);
    }


    //no idea if it works, couldn't test cause me and martin were still finishing the database
    @Override
    public UserCredentials findByUsernameOrEmail(String usernameOrEmail) {
        String jpql = "SELECT u FROM UserCredentials u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail";
        return em.createQuery(jpql, UserCredentials.class)
                .setParameter("usernameOrEmail", usernameOrEmail)
                .getResultStream()
                .findFirst() // Return the first match or empty if none found
                .orElse(null);
    }

    @Override
    public List<UserCredentials> findAll() {
        String jpql = "SELECT u FROM UserCredentials u";
        return em.createQuery(jpql, UserCredentials.class).getResultList();
    }



}

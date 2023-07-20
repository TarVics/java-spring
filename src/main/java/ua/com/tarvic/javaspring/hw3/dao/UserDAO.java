package ua.com.tarvic.javaspring.hw3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.tarvic.javaspring.hw3.models.User;

import java.util.List;


public interface UserDAO extends JpaRepository<User, Integer/*Primary Key Type*/> {

    // See https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
//    @Query("select u from User u where u.name = :#{#customer.firstname}")
//    List<User> findUsersByCustomersFirstname(@Param("customer") Customer customer);

//    @Query("select o from BusinessObject o where o.owner.emailAddress like "+
//            "?#{hasRole('ROLE_ADMIN') ? '%' : principal.emailAddress}")
//    List<BusinessObject> findBusinessObjectsForCurrentUser();

//    @Query("select u from User u where u.emailAddress = ?#{principal.emailAddress}")
//    List<User> findCurrentUserWithCustomQuery();

    @Query("select u from User u where u.name = :name")
    List<User> findUsersByName(@Param("name") String name);

    @Query("select u from User u where u.name = :#{#user.name}")
    List<User> findUsersByName(@Param("user") User user);

    User findUsersById(int id);

}

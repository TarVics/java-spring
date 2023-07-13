package ua.com.tarvic.javaspring.hw1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.com.tarvic.javaspring.hw1.models.Car;

import java.util.List;

@Repository
public class CarExDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> findCarsByPower(int power) {
//        for (EntityType<?> entity : entityManager.getMetamodel().getEntities()) {
//            final String className = entity.getName();
//            Query q = entityManager.createQuery("from " + className + " c");
//            q.getResultList().iterator();
//        }

        return entityManager.createQuery("from Car c where c.power = :power", Car.class)
                .setParameter("power", power)
                .getResultList();
    }

    List<Car> findCarsByProducer(String value) {
        return entityManager.createQuery("from Car c where c.producer = :producer", Car.class)
                .setParameter("producer", value)
                .getResultList();
    }

    @Transactional
    public void save(Car car) {
        entityManager.persist(car);
    }

    @Transactional
    public void update(Car car) {
        entityManager.merge(car);
    }

    public List<Car> findAll() {
//        return entityManager.createNativeQuery("select c.* from car c", Car.class)
//                .getResultList();
        return entityManager.createQuery("from Car c", Car.class)
                .getResultList();
    }

    public Car findOne(int id) {
        return entityManager.find(Car.class, id);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(findOne(id));
    }

}

package thirdsamost.thirdsamost.repository;

import thirdsamost.thirdsamost.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCar extends JpaRepository<Car, Long> {
}
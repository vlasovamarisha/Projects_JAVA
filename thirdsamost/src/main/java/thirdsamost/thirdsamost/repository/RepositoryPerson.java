package thirdsamost.thirdsamost.repository;

import thirdsamost.thirdsamost.model.Car;
import thirdsamost.thirdsamost.model.Person;
import thirdsamost.thirdsamost.repository.RepositoryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPerson extends JpaRepository<Person, Long> {
}
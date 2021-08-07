package rb.pplmngmntAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rb.pplmngmntAPI.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

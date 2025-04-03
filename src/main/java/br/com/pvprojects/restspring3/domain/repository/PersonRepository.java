package br.com.pvprojects.restspring3.domain.repository;

import br.com.pvprojects.restspring3.domain.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
  Optional<Person> findByEmailAndCpf(String email, String cpf);

  @Query(
      value =
          "SELECT * FROM Person " + "WHERE unaccent(first_name) ILIKE ('%' || unaccent(?1) || '%')",
      nativeQuery = true)
  List<Person> findByFirstNameIgnoreCaseAndAccent(String name);
}

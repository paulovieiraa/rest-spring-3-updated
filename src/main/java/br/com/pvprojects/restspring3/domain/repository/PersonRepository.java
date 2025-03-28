package br.com.pvprojects.restspring3.domain.repository;

import br.com.pvprojects.restspring3.domain.model.Person;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
  Optional<Person> findByEmailAndCpf(String email, String cpf);
}

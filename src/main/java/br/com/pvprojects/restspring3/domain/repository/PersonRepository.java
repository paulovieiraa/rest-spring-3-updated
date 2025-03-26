package br.com.pvprojects.restspring3.domain.repository;

import br.com.pvprojects.restspring3.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByEmailAndCpf(String email, String cpf);
}

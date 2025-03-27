package br.com.pvprojects.restspring3.domain.service;

import br.com.pvprojects.restspring3.api.v1.controller.PersonController;
import br.com.pvprojects.restspring3.api.v1.model.PersonModel;
import br.com.pvprojects.restspring3.api.v1.model.input.PersonInput;
import br.com.pvprojects.restspring3.domain.exception.BusinessException;
import br.com.pvprojects.restspring3.domain.model.Person;
import br.com.pvprojects.restspring3.domain.model.enums.Gender;
import br.com.pvprojects.restspring3.domain.repository.PersonRepository;
import br.com.pvprojects.restspring3.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ObjectMapper mapper;

    @Transactional
    public PersonModel create(PersonInput input) {
        Person person = new Person();

        String cpf = Util.cleanCpf(input.getCpf());
        String email = input.getEmail().toLowerCase().trim();
        this.validateUser(cpf, email);

        person.setFirstName(StringUtils.capitalize(input.getFirstName().trim()));
        person.setLastName(input.getLastName());
        person.setCpf(cpf);
        person.setGender(Gender.of(input.getGender()));
        person.setBirthDate(LocalDate.parse(input.getBirthDate()));
        person.setEmail(email);
        Person p = personRepository.saveAndFlush(person);
        log.info("Person created - Id: {}", p.getId());

        PersonModel personModel = mapper.convertValue(p, PersonModel.class);
        personModel.add(linkTo(methodOn(PersonController.class).getPerson(p.getId().toString())).withRel("getById"));
        personModel.add(linkTo(methodOn(PersonController.class).deletePerson(p.getId().toString())).withRel("deleteById"));
        return personModel;
    }

    public Page<PersonModel> getPeople(Pageable pageable) {

        Page<Person> people = personRepository.findAll(pageable);
        if (people.isEmpty()) {
            log.info("People not found");
            return new PageImpl<>(Collections.emptyList());
        }

        return people.map(person -> {
            PersonModel personModel = mapper.convertValue(person, PersonModel.class);
            personModel.setCpf("***." + person.getCpf().substring(3, 6) + ".***-" + person.getCpf().substring(9, 11));
            return personModel;
        });
    }

    public PersonModel getPerson(String id) {
        Optional<Person> optionalPerson = personRepository.findById(UUID.fromString(id));
        if (optionalPerson.isEmpty()) {
            log.info("Person not found. Id: {}", id);
            throw BusinessException.of("Pessoa não encontrada");
        }

        PersonModel personModel = mapper.convertValue(optionalPerson.get(), PersonModel.class);

        personModel.add(linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel());
        personModel.add(linkTo(methodOn(PersonController.class).deletePerson(id))
                .withRel("delete")
                .withType(HttpMethod.DELETE.name()));
        return personModel;
    }

    public void deletePerson(String id) {
        this.getPerson(id);
        personRepository.deleteById(UUID.fromString(id));
        log.info("Person deleted - Id: {}", id);
    }

    private void validateUser(String cpf, String email) {
        Optional<Person> person = personRepository.findByEmailAndCpf(email, cpf);
        if (person.isPresent()) {
            throw BusinessException.of("Usuario com cpf e email ja cadastrado");
        }
    }
}

package br.com.pvprojects.restspring3.domain.service;

import br.com.pvprojects.restspring3.api.v1.model.PersonModel;
import br.com.pvprojects.restspring3.api.v1.model.input.PersonInput;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

  PersonModel create(PersonInput input);

  Page<PersonModel> getPeople(Pageable pageable);

  PersonModel getPerson(String id);

  List<PersonModel> getPersonByName(String name);

  void deletePerson(String id);
}

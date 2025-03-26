package br.com.pvprojects.restspring3.api.v1.controller;

import br.com.pvprojects.restspring3.api.v1.model.PersonModel;
import br.com.pvprojects.restspring3.api.v1.model.input.PersonInput;
import br.com.pvprojects.restspring3.domain.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
@Tag(name = "Person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create Person",
            tags = {"Person"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UUID.class))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public PersonModel createPerson(@RequestBody @Valid PersonInput input) {
        PersonModel personModel = personService.create(input);
        PersonModel response = PersonModel.builder().id(personModel.getId()).build();
        response.add(personModel.getLinks());
        return response;
    }

    @GetMapping
    public Page<PersonModel> getPeople(Pageable pageable) {
        return personService.getPeople(pageable);
    }

    @GetMapping(value = "/{id}")
    public PersonModel getPerson(@PathVariable String id) {
        return personService.getPerson(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

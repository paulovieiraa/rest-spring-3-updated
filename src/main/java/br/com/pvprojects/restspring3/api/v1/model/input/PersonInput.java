package br.com.pvprojects.restspring3.api.v1.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonInput {

    private String firstName;

    private String lastName;

    @Size(min = 11, max = 14)
    private String cpf;

    private String birthDate;

    private String gender;

    private String email;
}

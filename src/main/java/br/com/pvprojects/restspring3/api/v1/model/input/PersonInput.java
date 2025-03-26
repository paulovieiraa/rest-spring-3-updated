package br.com.pvprojects.restspring3.api.v1.model.input;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonInput {

    @Schema(example = "Paulo")
    private String firstName;

    @Schema(example = "Vieira")

    private String lastName;

    @Size(min = 11, max = 14)
    @Schema(example = "870.194.410-09")
    private String cpf;

    @Schema(example = "1993-04-22")
    private String birthDate;

    @Schema(example = "0")
    private Integer gender;

    @Schema(example = "email@gmail.com")
    private String email;
}

package br.com.pvprojects.restspring3.api.v1.model;

import br.com.pvprojects.restspring3.domain.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "email", "cpf", "birthDate"})
public class PersonModel extends RepresentationModel<PersonModel> {

    @Schema(example = "02d9a07d-13b2-4b83-9f3f-5352d1daad46")
    private UUID id;

    @Schema(example = "Paulo")
    private String firstName;

    @Schema(example = "Vieira")
    private String lastName;

    @Schema(example = "87019441009")
    private String cpf;

    @Schema(example = "Vieira")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(example = "22-04-1993")
    private LocalDate birthDate;

    @Schema(example = "M")
    private Gender gender;

    @Override
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Links getLinks() {
        return super.getLinks();
    }
}

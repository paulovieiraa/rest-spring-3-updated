package br.com.pvprojects.restspring3.domain.model;

import br.com.pvprojects.restspring3.domain.model.enums.Gender;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid4")
  private UUID id;

  private String firstName;

  private String lastName;

  private String cpf;

  private String email;

  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @CreationTimestamp private OffsetDateTime createdAt;

  @UpdateTimestamp private OffsetDateTime updatedAt;

  //    @OneToMany
  //    private List<Address> addresses;
}

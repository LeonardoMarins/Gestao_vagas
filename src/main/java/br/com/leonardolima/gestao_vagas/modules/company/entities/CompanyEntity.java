package br.com.leonardolima.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Pattern(regexp = "^[^\\s]+$", message = "O nome de usuário não pode conter espaços em branco")
    private String username;
    @Email(message = "o campo email deve conter um email valido")
    private String email;
    @Length(min = 10, max = 100)
    private String password; 
    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createAt;
}

package br.com.leonardolima.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leonardolima.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity,UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
       // apenas por usar um findBy o JPA sabe que tem que fazer um select
       //no banco de dados
}

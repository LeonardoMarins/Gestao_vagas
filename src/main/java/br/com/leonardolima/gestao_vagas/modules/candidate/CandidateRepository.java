package br.com.leonardolima.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
       Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
       // apenas por usar um findBy o JPA sabe que tem que fazer um select
       //no banco de dados
}

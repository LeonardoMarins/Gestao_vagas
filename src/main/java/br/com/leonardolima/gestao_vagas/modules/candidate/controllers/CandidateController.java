package br.com.leonardolima.gestao_vagas.modules.candidate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardolima.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.leonardolima.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.leonardolima.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.leonardolima.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result =  this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "candidato", description = "informações do candidato")
    @Operation(summary = "Listagem de vagas disponiveis para o candidato", description = "Essa função é responsavel por listar todas as vagas disponiveis baseada no filtro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    @SecurityRequirement(name = "Jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}

package br.com.zup.handora.cadastrandopacotesunicos.pacote;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PacoteController.BASE_URI)
public class PacoteController {

    public final static String BASE_URI = "/pacotes";

    private final PacoteRepository pacoteRepository;

    public PacoteController(PacoteRepository pacoteRepository) {
        this.pacoteRepository = pacoteRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PacoteRequest pacoteRequest,
                                    UriComponentsBuilder ucb) {
        byte[] hash = new Cpf(pacoteRequest.getCpf()).getHash();

        if (pacoteRepository.existsByCpfTitular_Hash(hash)) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um pacote cadastrado para este titular."
            );
        }

        Pacote pacote = pacoteRepository.save(pacoteRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(pacote.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}

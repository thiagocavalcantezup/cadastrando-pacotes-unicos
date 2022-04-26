package br.com.zup.handora.cadastrandopacotesunicos.pacote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {

    boolean existsByCpfTitular_Hash(byte[] hash);

}

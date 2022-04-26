package br.com.zup.handora.cadastrandopacotesunicos.pacote;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "pacotes", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PACOTE_HASH_CPF", columnNames = {"hash_cpf"})})
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "numero_cpf", nullable = false, length = 14)),
            @AttributeOverride(name = "hash", column = @Column(name = "hash_cpf", nullable = false, length = 32))})
    private Cpf cpfTitular;

    @Column(nullable = false, length = 17)
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String numeroCelular;

    @Column(nullable = false)
    @Min(6)
    @Max(49)
    private Integer quantidadeDadosGigabytes;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCadastro = LocalDate.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Pacote() {}

    public Pacote(Cpf cpfTitular,
                  @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String numeroCelular,
                  @Min(6) @Max(49) Integer quantidadeDadosGigabytes) {
        this.cpfTitular = cpfTitular;
        this.numeroCelular = numeroCelular;
        this.quantidadeDadosGigabytes = quantidadeDadosGigabytes;
    }

    public Long getId() {
        return id;
    }

}

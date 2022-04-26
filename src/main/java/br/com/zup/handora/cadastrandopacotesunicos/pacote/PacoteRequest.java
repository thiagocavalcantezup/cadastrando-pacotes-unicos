package br.com.zup.handora.cadastrandopacotesunicos.pacote;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

public class PacoteRequest {

    @NotBlank
    @CPF
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String numeroCelular;

    @NotNull
    @Min(6)
    @Max(49)
    private Integer quantidadeDadosGigabytes;

    public PacoteRequest() {}

    public PacoteRequest(@NotBlank @CPF @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$") String cpf,
                         @NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String numeroCelular,
                         @NotNull @Min(6) @Max(49) Integer quantidadeDadosGigabytes) {
        this.cpf = cpf;
        this.numeroCelular = numeroCelular;
        this.quantidadeDadosGigabytes = quantidadeDadosGigabytes;
    }

    public Pacote toModel() {
        Cpf cpfTitular = new Cpf(cpf);

        return new Pacote(cpfTitular, numeroCelular, quantidadeDadosGigabytes);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public Integer getQuantidadeDadosGigabytes() {
        return quantidadeDadosGigabytes;
    }

}

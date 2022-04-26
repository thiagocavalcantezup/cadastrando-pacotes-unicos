package br.com.zup.handora.cadastrandopacotesunicos.pacote;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
public class Cpf {

    @Pattern(regexp = "^[0-9]{3}\\.\\*\\*\\*\\.\\*\\*\\*\\-[0-9]{2}$")
    private String numero;

    private byte[] hash;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Cpf() {}

    public Cpf(String cpf) {
        this.numero = anonymize(cpf);
        this.hash = hash(cpf);
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(hash);
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cpf other = (Cpf) obj;
        if (!Arrays.equals(hash, other.hash))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    public String anonymize(String cpf) {
        return cpf.replaceAll("([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})", "$1.***.***-$4");
    }

    public byte[] hash(String cpf) {
        try {
            return MessageDigest.getInstance("SHA3-256")
                                .digest(cpf.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um CPF: " + cpf);
        }
    }

    public byte[] getHash() {
        return hash;
    }

}

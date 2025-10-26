package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Conta implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Correntista correntista;
    private String numero;
    private Set<Transacao> transacoes = new HashSet<Transacao>();
    private LocalDate data;

    public Conta(Correntista correntista) {
        this.correntista = correntista;
    }
    
    public BigDecimal getSaldo() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transacao t : this.transacoes) {
            total = total.add(t.getValor());
        }
        return total;
    }
    public String getDataFormatada() {
        return data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
}

}
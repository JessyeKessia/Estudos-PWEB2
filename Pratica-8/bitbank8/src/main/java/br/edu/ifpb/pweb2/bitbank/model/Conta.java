package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conta implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "correntista_id", nullable = false)
    private Correntista correntista;

    private String numero;

    @OneToMany(mappedBy = "conta")
    private Set<Transacao> transacoes = new HashSet<Transacao>();

    // formatação de data
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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

    public void addTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
        transacao.setConta(this);
    }

}
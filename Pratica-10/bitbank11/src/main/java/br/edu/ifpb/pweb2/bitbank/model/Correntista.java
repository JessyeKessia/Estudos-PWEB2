package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Correntista implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo Obrigatório!")
    private String nome;

    @NotBlank(message = "Campo Obrigatório!")
    @Email(message = "Informe um email válido!")
    private String email;

    @NotBlank(message = "Campo Obrigatório!")
    @Size(min = 5, max = 70, message = "Senha deve ter entre 5 a 70 caracteres")
    private String senha;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    private boolean admin;

    
}
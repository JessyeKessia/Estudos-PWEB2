package br.edu.ifpb.pweb2.bitbank.model;

import java.util.List;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String username;
    private String password;
    private Boolean enable;

    @OneToMany(mappedBy = "username")
    @ToString.Exclude
    List<Authority> autorities;
}

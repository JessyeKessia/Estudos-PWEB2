package br.edu.ifpb.pweb2.bitbank.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.bitbank.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    public List<User> findByEnableTrue();
}

package br.com.diocesefranca.repository;
import br.com.diocesefranca.model.Fiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFiel extends JpaRepository<Fiel, Integer> {
    Optional<Fiel> findByEmail(String email);
}

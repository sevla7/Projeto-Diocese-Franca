package br.com.diocesefranca.repository;

import br.com.diocesefranca.model.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContato extends CrudRepository<Contato, Integer> {
}

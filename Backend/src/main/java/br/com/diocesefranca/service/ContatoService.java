package br.com.diocesefranca.service;

import br.com.diocesefranca.repository.IContato;
import br.com.diocesefranca.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private IContato repository;

    public Contato salvar(Contato contato) {
        return repository.save(contato);
    }

    public List<Contato> listarTodos() {
        return (List<Contato>) repository.findAll();
    }
}

package br.com.diocesefranca.service;

import br.com.diocesefranca.model.Fiel;
import br.com.diocesefranca.repository.IFiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FielService {

    private IFiel repository;

    public FielService(IFiel repository) {
        this.repository = repository;
    }

    @Autowired
    private PasswordEncoder encoder;

    public Fiel cadastrar(Fiel f) {
        f.setSenha(encoder.encode(f.getSenha()));
        return repository.save(f);
    }

    public List<Fiel> listarTodos() {
        return (List<Fiel>) repository.findAll();
    }

    public boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        return encoder.matches(senhaDigitada, senhaCriptografada);
    }

    public boolean autenticar(String email, String senha) {
        Optional<Fiel> fielOpt = repository.findByEmail(email);

        if (fielOpt.isPresent()) {
            Fiel fiel = fielOpt.get();
            return encoder.matches(senha, fiel.getSenha());
        }
        return false;
    }
}

package br.com.system.helpdesk.services.impl;

import br.com.system.helpdesk.domain.Pessoa;
import br.com.system.helpdesk.repositories.PessoaRepository;
import br.com.system.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> obj = repository.findByEmail(email);
        if (obj.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        // IMPORTANTE: Garanta que obj.getPerfis() não está vindo vazio aqui!
        return new UserSS(obj.get().getId(), obj.get().getEmail(), obj.get().getSenha(), obj.get().getPerfis());
    }
}

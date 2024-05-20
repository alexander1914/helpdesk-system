package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico buscarTecnicoPorId(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! ID: " + id));
    }

    public List<Tecnico> buscarTodosOsTecnicos() {
        return tecnicoRepository.findAll();
    }
}

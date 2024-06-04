package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Chamado;
import br.com.system.helpdesk.repositories.ChamadoRepository;
import br.com.system.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado buscarChamadoPorId(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamando não encontrado ! ID: " + id));
    }

    public List<Chamado> buscarTodosOsChamados(){
        return chamadoRepository.findAll();
    }
}

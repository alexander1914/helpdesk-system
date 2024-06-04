package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Chamado;
import br.com.system.helpdesk.domain.Cliente;
import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.domain.dtos.ChamadoDTO;
import br.com.system.helpdesk.domain.enums.Prioridade;
import br.com.system.helpdesk.domain.enums.Status;
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
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado buscarChamadoPorId(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamando não encontrado ! ID: " + id));
    }

    public List<Chamado> buscarTodosOsChamados(){
        return chamadoRepository.findAll();
    }

    public Chamado salvarChamado(ChamadoDTO chamadoDTO){
        return chamadoRepository.save(novoChamado(chamadoDTO));
    }

    private Chamado novoChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicoService.buscarTecnicoPorId(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.buscarClientePorId(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if (chamado.getId() != null){
            chamado.setId(chamadoDTO.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamado.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());

        return chamado;
    }
}

package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Pessoa;
import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.domain.dtos.TecnicoDTO;
import br.com.system.helpdesk.repositories.PessoaRepository;
import br.com.system.helpdesk.repositories.TecnicoRepository;
import br.com.system.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.system.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico buscarTecnicoPorId(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! ID: " + id));
    }

    public List<Tecnico> buscarTodosOsTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Tecnico salvarTecnico(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        validaPorCpfEemail(tecnicoDTO);
        Tecnico nevoTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(nevoTecnico);
    }

    public Tecnico atualizarTecnico(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico tecnicoOld = buscarTecnicoPorId(id);

        if(!tecnicoDTO.getSenha().equals(tecnicoOld.getSenha())){
            tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        }

        validaPorCpfEemail(tecnicoDTO);
        tecnicoOld = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnicoOld);
    }
    public void deletarTecnico(Integer id) {
        Tecnico tecnico = buscarTecnicoPorId(id);
        if (!tecnico.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("O técnico possui ordens de serviço e não pode ser deletado !!!");
        }else {
            tecnicoRepository.deleteById(id);
        }
    }
    private void validaPorCpfEemail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema !");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema !");
        }
    }
}

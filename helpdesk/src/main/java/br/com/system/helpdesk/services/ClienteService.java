package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Cliente;
import br.com.system.helpdesk.domain.Pessoa;
import br.com.system.helpdesk.domain.dtos.ClienteDTO;
import br.com.system.helpdesk.repositories.ClienteRepository;
import br.com.system.helpdesk.repositories.PessoaRepository;
import br.com.system.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.system.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente buscarClientePorId(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não existe ! ID: " + id));
    }

    public List<Cliente> buscarTodosOsClientes(){
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(ClienteDTO clienteDTO){
        clienteDTO.setId(null);
        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        validaPorCpfEemail(clienteDTO);
        Cliente novoCliente = new Cliente(clienteDTO);
        return clienteRepository.save(novoCliente);
    }

    public Cliente atualizarCliente(Integer id, ClienteDTO clienteDTO){
        clienteDTO.setId(id);
        Cliente cliente = buscarClientePorId(id);
        validaPorCpfEemail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Integer id){
        Cliente cliente = buscarClientePorId(id);

        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado !");
        }else {
            clienteRepository.deleteById(id);
        }
    }

    private void validaPorCpfEemail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema !");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema !");
        }
    }
}

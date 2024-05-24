package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Cliente;
import br.com.system.helpdesk.domain.dtos.ClienteDTO;
import br.com.system.helpdesk.domain.dtos.TecnicoDTO;
import br.com.system.helpdesk.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePeloId(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodosClientes(){
        List<Cliente> listaClientesEntidades = clienteService.buscarTodosOsClientes();
        List<ClienteDTO> listaClientesDTOs = listaClientesEntidades.stream()
                .map(clientes -> new ClienteDTO(clientes)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaClientesDTOs);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente novoCliente = clienteService.salvarCliente(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Integer id,
                                                       @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> deletarCliente(@PathVariable Integer id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

}

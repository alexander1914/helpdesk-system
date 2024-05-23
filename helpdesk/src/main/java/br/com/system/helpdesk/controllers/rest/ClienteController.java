package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Cliente;
import br.com.system.helpdesk.domain.dtos.ClienteDTO;
import br.com.system.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    //TODO: CONTINUE
}

package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Chamado;
import br.com.system.helpdesk.domain.dtos.ChamadoDTO;
import br.com.system.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> buscarChamadoId(@PathVariable Integer id){
        Chamado chamado = chamadoService.buscarChamadoPorId(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}

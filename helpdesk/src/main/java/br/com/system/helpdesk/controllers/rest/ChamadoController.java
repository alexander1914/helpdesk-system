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

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarTodosChamados(){
        List<Chamado> listaChamadosEntidade = chamadoService.buscarTodosOsChamados();
        List<ChamadoDTO> listaChamadosDTO = listaChamadosEntidade.stream()
                .map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaChamadosDTO);
    }
}

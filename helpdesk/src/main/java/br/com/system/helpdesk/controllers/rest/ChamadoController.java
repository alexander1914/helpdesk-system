package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Chamado;
import br.com.system.helpdesk.domain.dtos.ChamadoDTO;
import br.com.system.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> buscarChamadoId(@PathVariable Integer id) {
        Chamado chamado = chamadoService.buscarChamadoPorId(id);

        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarTodosChamados() {
        List<Chamado> listaChamadosEntidade = chamadoService.buscarTodosOsChamados();

        List<ChamadoDTO> listaChamadosDTO = listaChamadosEntidade.stream()
                .map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaChamadosDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> criarUmChamado(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        Chamado novoChamado = chamadoService.salvarChamado(chamadoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoChamado.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> atualizarChamado(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {

        Chamado chamado = chamadoService.atulizarChamado(id, chamadoDTO);

        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}

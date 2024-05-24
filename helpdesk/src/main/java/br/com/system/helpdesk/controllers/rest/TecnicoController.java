package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.domain.dtos.TecnicoDTO;
import br.com.system.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;
    //TODO: ResponseEntity é uma classe que representa toda resposta HTTP para trabalhar com APIsRESTfull

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> buscarTecnicoId(@PathVariable Integer id){
        Tecnico tecnico = tecnicoService.buscarTecnicoPorId(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> buscarTodosOsTecnicos(){
        List<Tecnico> listaTecnicosEntidade = tecnicoService.buscarTodosOsTecnicos();
        List<TecnicoDTO> listaTecnicosDTOs = listaTecnicosEntidade.stream()
                .map(tecnicos -> new TecnicoDTO(tecnicos)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaTecnicosDTOs);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> salvarTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico novoTecnico = tecnicoService.salvarTecnico(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoTecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> atualizarTecnico(@PathVariable Integer id,
                                                       @Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = tecnicoService.atualizarTecnico(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> deletarTecnico(@PathVariable Integer id){
        tecnicoService.deletarTecnico(id);
        return ResponseEntity.noContent().build();
    }
}

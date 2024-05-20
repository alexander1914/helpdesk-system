package br.com.system.helpdesk.controllers.rest;

import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.domain.dtos.TecnicoDTO;
import br.com.system.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;
    //TODO: ResponseEntity é uma classe que representa toda resposta HTTP para trabalhar com ApisRESTFull

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> buscarTecnicoId(@PathVariable Integer id){
        Tecnico tecnico = tecnicoService.buscarTecnicoPorId(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> buscarTodosOsTecnicos(){
        List<Tecnico> listaEntidades = tecnicoService.buscarTodosOsTecnicos();
        List<TecnicoDTO> listaDTOs = listaEntidades.stream()
                .map(tecnicos -> new TecnicoDTO(tecnicos)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTOs);
    }
}

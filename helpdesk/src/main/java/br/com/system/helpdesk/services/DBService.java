package br.com.system.helpdesk.services;

import br.com.system.helpdesk.domain.Chamado;
import br.com.system.helpdesk.domain.Cliente;
import br.com.system.helpdesk.domain.Tecnico;
import br.com.system.helpdesk.domain.enums.Perfil;
import br.com.system.helpdesk.domain.enums.Prioridade;
import br.com.system.helpdesk.domain.enums.Status;
import br.com.system.helpdesk.repositories.ChamadoRepository;
import br.com.system.helpdesk.repositories.ClienteRepository;
import br.com.system.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Alexander Oliveira", "4067889960",
                "alexander.oliveira99@gmail.com", encoder.encode("sep1914"));
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Nathalia Silva", "12345678990",
                "teste.silva@outlool.com", encoder.encode("123456"));

        Chamado chamado = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "CH00",
                "testando o chamando", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(chamado));
    }
}

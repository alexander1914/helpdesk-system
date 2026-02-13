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
        Tecnico tec1 = new Tecnico(null, "Alexander Oliveira", "40678899600",
                "alexander.oliveira99@gmail.com", encoder.encode("sep1914"));
        tec1.addPerfis(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Samuel Oliveira", "90274737060",
                "samuel.oliveira99@gmail.com", encoder.encode("123456"));
        tec2.addPerfis(Perfil.TECNICO);

        Tecnico tec3 = new Tecnico(null, "Joel Maciel", "81168226082",
                "joel.maiel@gmail.com", encoder.encode("123456"));
        tec3.addPerfis(Perfil.TECNICO);

        Tecnico tec4 = new Tecnico(null, "Eduarda Diamond", "94437994051",
                "eduarda.dioamond@gmail.com", encoder.encode("123456"));
        tec4.addPerfis(Perfil.TECNICO);

        Tecnico tec5 = new Tecnico(null, "Bruna Carlos", "07525513023",
                "bruna.carlos@gmail.com", encoder.encode("123456"));
        tec5.addPerfis(Perfil.TECNICO);

        Cliente cli1 = new Cliente(null, "Eduarda Silva", "12345678990",
                "eduarda.silva@gmail.com", encoder.encode("123456"));
        Cliente cli2 = new Cliente(null, "Maria Silva", "01319117082",
                "maria.silva@gmail.com", encoder.encode("123456"));
        Cliente cli3 = new Cliente(null, "Junior Joel", "88041397069",
                "junior.joel@gmail.com", encoder.encode("123456"));
        Cliente cli4 = new Cliente(null, "Carla Maia", "35992022066",
                "carla.maia@gmail.com", encoder.encode("123456"));
        Cliente cli5 = new Cliente(null, "João Silva", "33933595096",
                "joao.silva@gmail.com", encoder.encode("123456"));

        Chamado chamado = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "CH00",
                "testando o chamando", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(chamado));
    }
}

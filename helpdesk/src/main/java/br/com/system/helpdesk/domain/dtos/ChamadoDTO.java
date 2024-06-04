package br.com.system.helpdesk.domain.dtos;

import br.com.system.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID =1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String obeservacoes;
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Integer id, LocalDate dataAbertura, LocalDate dataFechamento,
                      Integer prioridade, Integer status, String titulo,
                      String obeservacoes, Integer tecnico,
                      Integer cliente, String nomeTecnico, String nomeCliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.obeservacoes = obeservacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.nomeTecnico = nomeTecnico;
        this.nomeCliente = nomeCliente;
    }

    public ChamadoDTO(Chamado chamado) {
        super();
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.obeservacoes = chamado.getObeservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObeservacoes() {
        return obeservacoes;
    }

    public void setObeservacoes(String obeservacoes) {
        this.obeservacoes = obeservacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}

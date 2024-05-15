package br.com.system.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

//TODO: Implementando da maneira antiga para fixar os conceitos da orientação por objetos em Java

public class Cliente extends Pessoa {
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}

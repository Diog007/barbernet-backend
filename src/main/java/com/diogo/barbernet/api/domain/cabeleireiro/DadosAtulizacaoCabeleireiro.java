package com.diogo.barbernet.api.domain.cabeleireiro;

public record DadosAtulizacaoCabeleireiro(Long id, String nome, String telefone, String email, String cpf) {

    public DadosAtulizacaoCabeleireiro(Cabeleireiro cabeleireiro){
        this(cabeleireiro.getId(), cabeleireiro.getNome(), cabeleireiro.getTelefone(), cabeleireiro.getEmail(), cabeleireiro.getCpf());
    }
}

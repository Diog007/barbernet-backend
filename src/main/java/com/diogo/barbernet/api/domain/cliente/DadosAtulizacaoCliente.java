package com.diogo.barbernet.api.domain.cliente;

public record DadosAtulizacaoCliente(Long id, String nome, String telefone, String email, String cpf) {


    public DadosAtulizacaoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
    }
}

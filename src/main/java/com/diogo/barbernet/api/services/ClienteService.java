package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.controller.EmailController;
import com.diogo.barbernet.api.domain.ValidacaoException;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.cliente.DadosAtulizacaoCliente;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import com.diogo.barbernet.api.domain.email.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EmailController emailController;

    public Cliente findById(Long id) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        return optionalCliente.orElse(null);
    }

    public Cliente cadastrarCliente(DadosCadastroCliente dados){
        Cliente newDados = new Cliente(dados);
        sendEmailCliente(newDados);
        return repository.save(newDados);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente atualizarCliente(Long id, DadosAtulizacaoCliente dados) {
        Cliente cliente = findById(id);
        cliente.setNome(dados.nome());
        cliente.setTelefone(dados.telefone());
        cliente.setEmail(dados.email());
        cliente.setCpf(dados.cpf());

        return repository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = findById(id);
        if(cliente.getAgendamentos().size() > 0) {
            throw new ValidacaoException("cliente possui agendamentos");
        }
        repository.deleteById(id);
    }

    private void sendEmailCliente(Cliente cliente) {
        String assunto = "Bem-vindo(a) à Barbernet!";
        String mensagem = String.format(
                "Olá, %s,\n\n" +
                        "Seja bem-vindo(a) à nossa barbearia! Estamos muito contentes em tê-lo(a) conosco.\n" +
                        "Aqui estão alguns detalhes do seu cadastro:\n\n" +
                        "Nome: %s\n" +
                        "E-mail: %s\n" +
                        "Telefone: %s\n\n" +
                        "Se precisar de qualquer coisa, não hesite em nos contatar.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe Barbernet",
                cliente.getNome(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone()
        );

        EmailDTO email = new EmailDTO("barbernet.api@gmail.com", cliente.getEmail(), assunto, mensagem);
        this.emailController.sendingEmail(email);
    }

}

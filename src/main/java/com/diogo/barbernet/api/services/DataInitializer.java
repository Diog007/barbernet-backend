package com.diogo.barbernet.api.services;

import com.diogo.barbernet.api.domain.agendamento.Agendamento;
import com.diogo.barbernet.api.domain.agendamento.AgendamentoRepository;
import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.CabeleireiroRepository;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.ClienteRepository;
import com.diogo.barbernet.api.domain.usuario.Usuario;
import com.diogo.barbernet.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CabeleireiroRepository cabeleireiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(String... args) throws Exception {
        String login = "test@test.com";
        String senha = "123";
        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuario usuario = new Usuario(null, login, senhaCriptografada);
        if (!usuarioRepository.existsByLogin(login)) {
            usuarioRepository.save(usuario);
        }

        Cliente cliente1 = new Cliente(1L, "Diogo", "119591557", "diogo@gmial.com", "1211515631");
        Cliente cliente2 = new Cliente(2L, "Malcon", "1195843957", "malcon@gmail.com", "263515611521");
        Cliente cliente3 = new Cliente(3L, "Danilo", "1195963145957", "danilo@gmail.com", "15212611121");
        if (!clienteRepository.existsByCpf(cliente1.getCpf())) {
            clienteRepository.save(cliente1);
        }
        if (!clienteRepository.existsByCpf(cliente2.getCpf())) {
            clienteRepository.save(cliente2);
        }
        if (!clienteRepository.existsByCpf(cliente3.getCpf())) {
            clienteRepository.save(cliente3);
        }

        Cabeleireiro cabeleireiro1 = new Cabeleireiro(1L, "Robert", "118533838", "robert@gmial.com", "73373773");
        Cabeleireiro cabeleireiro2 = new Cabeleireiro(2L, "Renato", "11683853", "renato@gmial.com", "7838378373");
        Cabeleireiro cabeleireiro3 = new Cabeleireiro(3L, "Omar", "113853883", "omar@gmial.com", "45663753");
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro1.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro1);
        }
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro2.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro2);
        }
        if (!cabeleireiroRepository.existsByCpf(cabeleireiro3.getCpf())) {
            cabeleireiroRepository.save(cabeleireiro3);
        }

        LocalDateTime dataHora1 = LocalDateTime.parse("2024-03-01T10:15:30");
        LocalDateTime dataHora2 = LocalDateTime.parse("2024-03-02T10:15:30");
        LocalDateTime dataHora3 = LocalDateTime.parse("2024-03-03T10:15:30");

        Agendamento agendamento1 = new Agendamento(null, cliente1, cabeleireiro1, dataHora1);
        Agendamento agendamento2 = new Agendamento(null, cliente2, cabeleireiro2, dataHora2);
        Agendamento agendamento3 = new Agendamento(null, cliente3, cabeleireiro3, dataHora3);
        // Certifique-se de tratar possíveis exceções relacionadas à data e hora aqui
        agendamentoRepository.save(agendamento1);
        agendamentoRepository.save(agendamento2);
        agendamentoRepository.save(agendamento3);
    }
}


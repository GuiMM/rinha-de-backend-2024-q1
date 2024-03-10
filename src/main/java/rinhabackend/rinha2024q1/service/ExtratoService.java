package rinhabackend.rinha2024q1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rinhabackend.rinha2024q1.model.Cliente;
import rinhabackend.rinha2024q1.model.Extrato;
import rinhabackend.rinha2024q1.model.Saldo;
import rinhabackend.rinha2024q1.model.Transacao;
import rinhabackend.rinha2024q1.repository.ClientRepository;
import rinhabackend.rinha2024q1.repository.TransacaoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ExtratoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ClientRepository clienteRepository;

    public Extrato listarExtrato (Integer id){
        Extrato extrato = null;
        Cliente cliente = clienteRepository.getCliente(id);
        if(cliente != null) {
            Saldo saldo = Saldo.builder().total(cliente.getSaldo()).limite(cliente.getLimite()).dataExtrato(LocalDateTime.now()).build();
            List<Transacao> transacoes = transacaoRepository.listaTransacaoPorCliente(id);
            extrato = Extrato.builder().saldo(saldo).ultimasTransacoes(transacoes).build();
        }
        return extrato;
    }
}

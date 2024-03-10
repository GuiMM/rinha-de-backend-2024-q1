package rinhabackend.rinha2024q1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rinhabackend.rinha2024q1.Exception.ClienteNaoEncontradoException;
import rinhabackend.rinha2024q1.Exception.SaldoInconsistenteException;
import rinhabackend.rinha2024q1.Exception.TransacaoInvalidaException;
import rinhabackend.rinha2024q1.enums.TipoTransacao;
import rinhabackend.rinha2024q1.model.*;
import rinhabackend.rinha2024q1.repository.ClientRepository;
import rinhabackend.rinha2024q1.repository.TransacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private ClientRepository clienteRepository;

    @Transactional
    public RespostaTransacao processaTrasacao(Integer clienteId, RequestTransacao body) {
        if(!transacaoValida(body))
            throw new TransacaoInvalidaException();

        Cliente cliente = clienteRepository.getCliente(clienteId);
        if( cliente == null)
            throw new ClienteNaoEncontradoException();
        TransacaoDTO transacao = TransacaoDTO.builder().valor(body.getValor()).tipo(body.getTipo()).descricao(body.getDescricao()).clienteId(clienteId).build();
        int checkInsercao = repository.insertTransacao(transacao);
        if(checkInsercao == 0)
            throw new SaldoInconsistenteException();

        Integer novoSaldo = body.getTipo().equals(TipoTransacao.CREDITO.getTipo()) ? cliente.getSaldo() + body.getValor() : cliente.getSaldo() - body.getValor();
        return RespostaTransacao.builder().saldo(novoSaldo).limit(cliente.getLimite()).build();
    }

    private boolean transacaoValida(RequestTransacao body) {
        return (body.getTipo().equals(TipoTransacao.CREDITO.getTipo()) || body.getTipo().equals(TipoTransacao.DEBITO.getTipo()) ) && body.getDescricao().length() <= 10;
    }

//    public Integer insereTransacao(Integer id, RequestTransacao body){
//
//    }

//    public Extrato listaExtrato(Integer id){
//
//        List<Transacao> transacoes = repository.listaTransacaoPorCliente(id);
//    }

}

package rinhabackend.rinha2024q1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rinhabackend.rinha2024q1.model.Extrato;
import rinhabackend.rinha2024q1.model.RequestTransacao;
import rinhabackend.rinha2024q1.model.Transacao;
import rinhabackend.rinha2024q1.repository.TransacaoRepository;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

//    public Integer insereTransacao(Integer id, RequestTransacao body){
//
//    }

//    public Extrato listaExtrato(Integer id){
//
//        List<Transacao> transacoes = repository.listaTransacaoPorCliente(id);
//    }

}

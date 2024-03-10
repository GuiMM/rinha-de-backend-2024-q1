package rinhabackend.rinha2024q1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rinhabackend.rinha2024q1.Exception.ClienteNaoEncontradoException;
import rinhabackend.rinha2024q1.Exception.SaldoInconsistenteException;
import rinhabackend.rinha2024q1.Exception.TransacaoInvalidaException;
import rinhabackend.rinha2024q1.model.Extrato;
import rinhabackend.rinha2024q1.model.RequestTransacao;
import rinhabackend.rinha2024q1.model.RespostaTransacao;
import rinhabackend.rinha2024q1.repository.ClientRepository;
import rinhabackend.rinha2024q1.service.ExtratoService;
import rinhabackend.rinha2024q1.service.TransacaoService;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    ClientRepository repo;

    @Autowired
    ExtratoService extratoService;

    @Autowired
    TransacaoService transacaoService;

    //testezinho
    @GetMapping(path = "/{id}/transacoes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespostaTransacao> transacaoExemplo(@PathVariable int id){
        return ResponseEntity.ok().body(RespostaTransacao.builder().saldo(1000).build());

    }

    @PostMapping(path="/{id}/transacoes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RespostaTransacao> transacao (@PathVariable int id, @RequestBody RequestTransacao body){

        try {
            RespostaTransacao resposta = transacaoService.processaTrasacao(id,body);
            return ResponseEntity.ok().body(resposta);
        }catch(ClienteNaoEncontradoException ex) {
            return ResponseEntity.notFound().build();
        }catch(SaldoInconsistenteException ex){
            return ResponseEntity.unprocessableEntity().build();
        }catch (TransacaoInvalidaException ex){
            return ResponseEntity.unprocessableEntity().build();
        }


    }

    @GetMapping(path="/{id}/extrato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Extrato> listarExtrato(@PathVariable int id){
        Extrato extrato = extratoService.listarExtrato(id);
        if(extrato == null )
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(extrato);
    }

    @GetMapping(path = "/teste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testeBanco(){
        String result = repo.getInfoClients();
        return ResponseEntity.ok().body(result);
    }
}

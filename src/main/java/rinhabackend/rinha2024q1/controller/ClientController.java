package rinhabackend.rinha2024q1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rinhabackend.rinha2024q1.model.RequestTransacao;
import rinhabackend.rinha2024q1.model.RespostaTransacao;
import rinhabackend.rinha2024q1.repository.ClientRepository;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    ClientRepository repo;

    //testezinho
    @GetMapping(path = "/{id}/transacoes", produces = "application/json")
    public ResponseEntity<RespostaTransacao> transacaoExemplo(@PathVariable int id){
        return ResponseEntity.ok().body(RespostaTransacao.builder().saldo(1000).build());

    }

    @PostMapping(path="/clientes/{id}/transacoes", produces = "application/json")
    public ResponseEntity<RespostaTransacao> transacao (@PathVariable int id, @RequestBody RequestTransacao body){


        return ResponseEntity.ok().body(RespostaTransacao.builder().saldo(1000).build());

    }


    @GetMapping(path = "/teste", produces = "application/json")
    public ResponseEntity<String> testeBanco(){
        String result = repo.getInfoClients();
        return ResponseEntity.ok().body(result);
    }
}

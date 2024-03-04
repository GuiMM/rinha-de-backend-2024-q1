package rinhabackend.rinha2024q1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Extrato {

    private Saldo saldo;
    @JsonProperty(value = "ultimas_transacoes")
    private List<Transacao> ultimasTransacoes;


}

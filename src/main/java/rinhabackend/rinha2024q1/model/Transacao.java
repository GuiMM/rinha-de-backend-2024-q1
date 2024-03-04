package rinhabackend.rinha2024q1.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Transacao {

    private Integer valor;
    private String tipo;
    private String descricao;
    private LocalDateTime realizadaEm;
}


package rinhabackend.rinha2024q1.model;

import lombok.Data;

@Data
public class RequestTransacao {
    private Integer valor;
    private String tipo;
    private String descricao;
}

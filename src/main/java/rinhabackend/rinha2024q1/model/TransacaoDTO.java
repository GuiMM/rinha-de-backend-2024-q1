package rinhabackend.rinha2024q1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoDTO {

    private Integer valor;
    private String tipo;
    private String descricao;
    private Integer clienteId;
}

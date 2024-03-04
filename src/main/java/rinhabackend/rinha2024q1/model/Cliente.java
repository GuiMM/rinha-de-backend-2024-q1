package rinhabackend.rinha2024q1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {

    private Integer id;
    private Integer limite;
    private Integer saldo;
}

package rinhabackend.rinha2024q1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespostaTransacao {
    public Integer limit;
    public Integer saldo;
}

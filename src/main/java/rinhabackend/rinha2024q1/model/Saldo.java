package rinhabackend.rinha2024q1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Saldo {

    private Integer total;

    @JsonProperty(value = "data_extrato")
    private LocalDateTime dataExtrato;

    private Integer limite;
}

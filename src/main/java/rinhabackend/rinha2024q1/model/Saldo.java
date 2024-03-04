package rinhabackend.rinha2024q1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class Saldo {

    private Integer total;

    @JsonProperty(value = "data_extrato")
    private LocalDate dataExtrato;

    private Integer limite;
}

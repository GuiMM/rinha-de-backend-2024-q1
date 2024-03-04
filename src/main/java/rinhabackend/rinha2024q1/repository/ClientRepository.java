package rinhabackend.rinha2024q1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import rinhabackend.rinha2024q1.model.Cliente;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ClientRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getInfoClients(){
        String result ="";

        result = jdbcTemplate.queryForList("Select * from cliente", (SqlParameterSource) null).toString();
        return result;
    }

    public Cliente getCliente(Integer id){

        Map<String,Integer> map = new HashMap<>();
        map.put("id", id);

        String query =
                " SELECT cli.id\n" +
                ", limite\n" +
                ", saldoInicial + (select valor from transacao where clienteid = :id and tipo = 'c')\n" +
                "- (select valor from transacao where clienteid = :id and tipo = 'd') as saldo\n" +
                "\n" +
                "FROM cliente cli\n" +
                "WHERE cli.id = :id \n";

        return jdbcTemplate.query(query, map,
                (resultset, rowNum) -> Cliente.builder()
                        .id(resultset.getInt("id"))
                        .saldo(resultset.getInt("saldo"))
                        .limite(resultset.getInt("limite")).build()).get(0);
    }
}

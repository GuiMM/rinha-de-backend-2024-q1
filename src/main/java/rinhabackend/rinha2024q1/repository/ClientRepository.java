package rinhabackend.rinha2024q1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getInfoClients(){
        String result ="";

        result = jdbcTemplate.queryForList("Select * from Cliente", (SqlParameterSource) null).toString();
        return result;
    }
}

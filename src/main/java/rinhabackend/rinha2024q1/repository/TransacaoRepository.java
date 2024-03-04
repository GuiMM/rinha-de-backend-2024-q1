package rinhabackend.rinha2024q1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import rinhabackend.rinha2024q1.model.Transacao;
import rinhabackend.rinha2024q1.model.TransacaoDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class TransacaoRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int insertTransacao(TransacaoDTO transacao){
        Map<String,String> map = new HashMap<>();
        map.put(":clientId", transacao.getClienteId().toString());
        map.put(":valor", transacao.getValor().toString());
        map.put(":tipo", String.valueOf(transacao.getTipo()));
        map.put(":descricao", transacao.getDescricao());

        String query = "INSERT INTO transacao " +
                " (valor, " +
                " tipo ," +
                " descricao ,\n" +
                " realizada_em ,\n" +
                " cliente_id ) VALUES " +
                " ( :valor, :tipo, :descricao, sysdate, :clientId )";
        return jdbcTemplate.update(query, map);
    }

    public List<Transacao> listaTransacaoPorCliente(Integer id){
        Map<String,Integer> map = new HashMap<>();
        map.put("clientId", id);

        String query = "SELECT * FROM transacao where clienteId = :clientId ";
        List<Transacao> result = jdbcTemplate.query(query, map,
                (rs, rowNum) -> Transacao.builder()
                        .valor(rs.getInt("valor"))
                        .tipo(rs.getString("tipo"))
                        .descricao(rs.getString("descricao"))
                        .realizadaEm(rs.getTimestamp("realizadaem").toLocalDateTime())
                        .build());
        return result;
    }

}

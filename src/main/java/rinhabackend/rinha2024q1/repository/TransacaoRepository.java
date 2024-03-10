package rinhabackend.rinha2024q1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import rinhabackend.rinha2024q1.enums.TipoTransacao;
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
        Map<String, Object>  map = new HashMap<>();
        map.put("clientId", transacao.getClienteId());
        map.put("valor", transacao.getValor());
        map.put("tipo", String.valueOf(transacao.getTipo()));
        map.put("descricao", transacao.getDescricao());


        String queryCreditos = "INSERT INTO transacao " +
                " (valor, " +
                " tipo ," +
                " descricao ,\n" +
                " realizadaem ,\n" +
                " clienteid ) VALUES " +
                " ( :valor, :tipo, :descricao, CURRENT_TIMESTAMP, :clientId )";


        String queryDebitos =
                "  with insert_values as (\n" +
                        "     select :valor as valor, :tipo as tipo, :descricao as descricao, CURRENT_TIMESTAMP as realizadaem, :clientId as clienteid" +
                        "     ) " +
                        "INSERT INTO transacao (valor, tipo, descricao, realizadaem, clienteid) \n" +
                        "SELECT insert_values.valor, insert_values.tipo, insert_values.descricao, insert_values.realizadaem, insert_values.clienteid\n" +
                        "FROM cliente c, insert_values\n" +
                        "WHERE id = :clientId \n" +
                        "AND -1 * limite <= (saldoInicial + (\n" +
                        "    SELECT CASE WHEN sum(valor) IS NOT NULL THEN sum(valor) ELSE 0 END\n" +
                        "    FROM transacao\n" +
                        "    WHERE clienteId = :clientId AND tipo = 'c'\n" +
                        ") - (\n" +
                        "    SELECT CASE WHEN sum(valor) IS NOT NULL THEN sum(valor) ELSE 0 END\n" +
                        "    FROM transacao\n" +
                        "    WHERE clienteId = :clientId AND tipo = 'd'\n" +
                        ")) - :valor ";

        String query = transacao.getTipo().equals(TipoTransacao.CREDITO.getTipo()) ? queryCreditos : queryDebitos;

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

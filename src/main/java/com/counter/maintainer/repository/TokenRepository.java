package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.TokenType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.exceptions.TokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Token createToken(Token token) {
        String createQuery = "insert into token set customerId=?1, servicePriority=?2, serviceID=(select serviceId from serviceTypes where name=?3)";
        KeyHolder idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(createQuery,
                                                                   new String[]{"id"});

                ps.setString(1, String.valueOf(token.getCustomerId()));
                ps.setString(2, token.getServicePriority().name());
                ps.setString(3, String.valueOf(token.getTokenType()));

                return ps;
            }
        }, idHolder);

        token.setTokenId(Long.valueOf(idHolder.getKey().intValue()));
        return token;

    }


    @Transactional(readOnly=true)
    public Token getToken(long tokenId) {
        String query = "select t.tokenId, t.customerId, t.servicePriority, t.inQ, t.comments, t.actionItems, "
                           + "t.status, c.counterId, st.name from token as t  "
                           + "left join counterStatus as c on t.tokenId=c.tokenId "
                           + "left join serviceTypes as st on t.serviceID=st.serviceID "
                           + "where t.tokenId =?";

        List<Token> tokenList = jdbcTemplate.query(query, new Object[]{tokenId}, new TokenRowMapper());
        if (tokenList.size() == 0) {
            throw new TokenNotFoundException(tokenId);
        }
        return tokenList.get(0);
    }


    public void updateCounter(Long tokenId, Long counterId, Boolean inQ) {
        if(inQ != true) {
            // existing entry
            jdbcTemplate.update("update counterStatus set counterId=?2, inQ=?3 where tokenId=?1", new Object[] { tokenId, counterId, inQ });
        } else {
            jdbcTemplate.update("insert into counterStatus(tokenId, counterId, inQ) values(?1,?2, ?3)", new Object[] { tokenId, counterId, inQ });
        }

    }

    public void updateTokenComments(Long tokenId, String comment) {
        jdbcTemplate.update("update token set comments = CONCAT(comments, ' ; ', ?2) where tokenId=?1", new Object[] { tokenId, comment });
    }

    public void updateTokenStatus(Long tokenId, TokenStatus status, boolean inQ){
        jdbcTemplate.update("update token set status=?2, inQ=?3 where tokenId=?1", new Object[]{tokenId, status.name(), inQ });
    }

}

class TokenRowMapper implements RowMapper<Token>
{
    @Override
    public Token mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Token token = new Token();
        token.setTokenId(rs.getLong("tokenId"));
        token.setCustomerId(rs.getLong("customerId"));
        token.setInQ(rs.getBoolean("inQ"));
        token.setStatus(TokenStatus.valueOf(rs.getString("status")));
        token.setComments(rs.getString("comments"));
        token.setTokenType(TokenType.valueOf(rs.getString("name")));
        token.setServicePriority(ServicePriority.valueOf(rs.getString("servicePriority")));
        token.setCounterId(rs.getLong("counterId"));

        return token;
    }
}

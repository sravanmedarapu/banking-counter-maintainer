package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.TokenType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import com.counter.maintainer.exceptions.CustomerException;
import com.counter.maintainer.exceptions.InvalidTokenException;
import com.counter.maintainer.exceptions.RepositoryException;
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

/**
 * Utility Class to interact with Token table
 */
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Creates the token
     *
     * @param token
     * @return Token
     * @throws TokenNotFoundException if token not found
     */
    public Token createToken(Token token) throws RepositoryException {
        try {
            String createQuery = "insert into token set customerId=?1, tokenPriority=?2, tokenTypeId=(select tokenTypeId from TokenType where name=?3)";
            KeyHolder idHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps = connection.prepareStatement(createQuery,
                                                                       new String[] { "id" });

                    ps.setString(1, String.valueOf(token.getCustomerId()));
                    ps.setString(2, token.getServicePriority().name());
                    ps.setString(3, String.valueOf(token.getTokenType()));

                    return ps;
                }
            }, idHolder);

            token.setTokenId(Long.valueOf(idHolder.getKey().intValue()));
            return token;
        } catch (Exception e) {
            throw new RepositoryException("Unable to create Token:" + e.getMessage(), e);
        }

    }

    /**
     * Returns the token
     *
     * @param tokenId
     * @return Token
     * @throws TokenNotFoundException if token not found
     */
    @Transactional(readOnly = true)
    public Token getToken(long tokenId) {
        String query = "select t.tokenId, t.customerId, t.tokenPriority, t.comments, "
                           + "t.status, c.counterId, tt.name from token as t  "
                           + "left join TokenStatus as c on t.tokenId=c.tokenId "
                           + "left join TokenType as tt on t.tokenTypeId=tt.tokenTypeId "
                           + "where t.tokenId =?";

        List<Token> tokenList = jdbcTemplate.query(query, new Object[] { tokenId }, new TokenRowMapper());
        if (tokenList.size() == 0) {
            throw new TokenNotFoundException(tokenId);
        }
        return tokenList.get(0);
    }

    /**
     * Checks if token exist with tokenId
     *
     * @param tokenId
     * @return
     */
    public Boolean isTokenExist(long tokenId) {
        Long count = jdbcTemplate.queryForObject("select count(*) from token where tokenId=?1", new Object[] { tokenId }, Long.class);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the token counter
     *
     * @param tokenId
     * @return
     * @throws TokenNotFoundException if token not found
     */
    public void updateCounter(Long tokenId, Long counterId) {
        if (!isTokenExist(tokenId)) {
            throw new InvalidTokenException("No token found with tokenID:" + tokenId);
        }
        jdbcTemplate.update("update TokenStatus set counterId=?2 where tokenId=?1", new Object[] { tokenId, counterId });

    }

    public void addTokenToCounter(Long tokenId, Long counterId) {
        jdbcTemplate.update("insert into TokenStatus(tokenId, counterId) values(?1,?2)", new Object[] { tokenId, counterId });
    }

    /**
     * Updates the token comments
     *
     * @param tokenId
     * @return
     * @throws TokenNotFoundException if token not found
     */
    public void updateTokenComments(Long tokenId, String comment) {
        if (!isTokenExist(tokenId)) {
            throw new InvalidTokenException("No token found with tokenID:" + tokenId);
        }
        jdbcTemplate.update("update token set comments = CONCAT(comments, ' ; ', ?2) where tokenId=?1", new Object[] { tokenId, comment });
    }

    /**
     * Updates the token status
     *
     * @param tokenId
     * @return
     * @throws TokenNotFoundException if token not found
     */
    public void updateTokenStatus(Long tokenId, TokenStatus status) {
        if (!isTokenExist(tokenId)) {
            throw new InvalidTokenException("No token found with tokenID:" + tokenId);
        }
        jdbcTemplate.update("update token set status=?2 where tokenId=?1", new Object[] { tokenId, status.name() });
    }

    /**
     * Updates the token queue status
     *
     * @param tokenId
     * @return
     * @throws TokenNotFoundException if token not found
     */
    public void updateTokenQueueStatus(Long tokenId, Long counterId, Boolean inQ) {
        if (!isTokenExist(tokenId)) {
            throw new InvalidTokenException("No token found with tokenID:" + tokenId);
        }
        jdbcTemplate.update("update TokenStatus set inQ=?3 where tokenId=?1 and counterId=?2", new Object[] { tokenId, counterId, inQ });

    }

}

class TokenRowMapper implements RowMapper<Token> {
    @Override
    public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
        Token token = new Token();
        token.setTokenId(rs.getLong("tokenId"));
        token.setCustomerId(rs.getLong("customerId"));
        token.setStatus(TokenStatus.valueOf(rs.getString("status")));
        token.setComments(rs.getString("comments"));
        token.setTokenType(TokenType.valueOf(rs.getString("name")));
        token.setServicePriority(ServicePriority.valueOf(rs.getString("tokenPriority")));
        token.setCounterId(rs.getLong("counterId"));

        return token;
    }
}

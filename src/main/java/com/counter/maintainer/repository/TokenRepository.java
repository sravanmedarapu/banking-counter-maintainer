package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.ServicePriority;
import com.counter.maintainer.data.contracts.ServiceType;
import com.counter.maintainer.data.contracts.Token;
import com.counter.maintainer.data.contracts.TokenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Token createToken(Token token) {
        String createQuery = "insert into token set(customerId, serviceID) values ";
        return null;

    }

    public List<Token> findAll() {
        return null;

    }


    @Transactional(readOnly=true)
    public Token getToken(long tokenId) {
        String query = "select t.tokenId, t.customerId, t.inQ, t.comments, t.actionItems, "
                           + "t.status, c.counterId, st.name from token as t  "
                           + "inner join counterStatus as c on t.tokenId=c.tokenId "
                           + "inner join serviceTypes as st on t.serviceID=st.serviceID "
                           + "where t.tokenId =?";

        return jdbcTemplate.queryForObject("select  * from token", new Object[]{tokenId}, new TokenRowMapper());
    }


    @Query(value="update token set counterId=?2 where tokenId=?1", nativeQuery = true)
    public void updateCounter(Long tokenId, Long counterId) {

    }

    @Query(value="update token set status=?2, inQ=?3 where tokenId=?1", nativeQuery = true)
    public void updateTokenStatus(Long tokenId, TokenStatus status, boolean inQ){

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
        ServiceType serviceName = ServiceType.valueOf(rs.getString("serviceName"));
        token.setInQ(rs.getBoolean("inQ"));
        token.setStatus(TokenStatus.valueOf(rs.getString("status")));
        //token.setServicePriority();

       // ServiceType serviceType = ServiceType.valueOf(serviceName);
        return token;
    }
}

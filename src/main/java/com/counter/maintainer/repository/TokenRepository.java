package com.counter.maintainer.repository;

import com.counter.maintainer.data.contracts.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token save(Token token);

    List<Token> findAll();

    Token findOne(Long tokenId);

    @Query(value="update token set counterId=?2 where tokenId=?1", nativeQuery = true)
    void updateCounter(Long tokenId, Long counterId);

}

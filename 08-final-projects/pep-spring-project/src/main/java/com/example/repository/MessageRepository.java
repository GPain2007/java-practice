package com.example.repository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
   
    @Transactional
    @Query("FROM Message WHERE posted_by = :accountId" )
    List<Message> findByPosted_by(@Param("accountId") Integer posted_by);

    @Transactional
    @Query("FROM Message WHERE posted_by = :id")
    Message findMessageByPosted_by(@Param("id") Integer posted_by);

    @Transactional
    @Query("FROM Message WHERE message_id = :id")
    Optional<Message>  findByMessageId(@Param("id") Integer message_id);
}

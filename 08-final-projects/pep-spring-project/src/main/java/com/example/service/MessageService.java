package com.example.service;

import com.example.entity.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public Optional<Message> getByMessageId (Integer id){
        return messageRepository.findByMessageId(id);
    }


    public Message saveMessage(Message message) {
        
        if (message.getMessage_text().isBlank() || message.getMessage_text().length() > 255) {
            throw new IllegalArgumentException("Message text should not be blank and should be under 255 characters.");
        }
        

        return messageRepository.save(message);
    }
    public List<Message> getAllMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPosted_by(accountId);
    }
    public Message getUserByPosted_by(Integer id){
        return messageRepository.findMessageByPosted_by(id);
    }

    public void deleteMessageById(Integer id) {
        messageRepository.deleteById(id);
    }

   
   

   
}

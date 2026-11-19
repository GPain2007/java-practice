package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {


    @Autowired
    private MessageService messageService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerAccount(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password =request.get("password");

        if (username.isBlank() || password.length() < 4) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (accountService.getAccountByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Account createdAccount = accountService.createAccount(username, password);
        return ResponseEntity.ok(createdAccount);
    }

    

    @PostMapping("/login")
    public ResponseEntity<Object> loginAccount(@RequestBody Account account) {
        if (accountService.isLoginValid(account)) {
            return ResponseEntity.ok(accountService.getAccountByUsername(account.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Object> getMessageById(@PathVariable("message_id") Integer id) {
        Optional<Message> message = messageService.getMessageById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("messages")
    public ResponseEntity<Object> createMessage(@RequestBody Message message) {
        Optional<Account> user = accountService.getAccountById(message.getPosted_by());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(message.getMessage_text().isBlank()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(messageService.saveMessage(message));
    }
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer id) {
        if (messageService.getMessageById(id).isPresent()) {
            messageService.deleteMessageById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("messages/{id}")
    private ResponseEntity<Integer> updateMessage(@PathVariable Integer id, @RequestBody Message message) {
        if (messageService.getMessageById(id).isPresent() && message.getMessage_text().length() < 255 && !message.getMessage_text().isEmpty()) { 
                messageService.saveMessage(message);
                return new ResponseEntity<>(1, HttpStatus.OK); 
            }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
   
    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable("account_id") Integer accountId) {
        List<Message> messages = messageService.getAllMessagesByAccountId(accountId);
        return ResponseEntity.ok(messages);
    }
}

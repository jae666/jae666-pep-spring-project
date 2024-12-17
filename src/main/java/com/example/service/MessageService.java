package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Create a new message
    public Message createMessage(Message message) {
        // Validate message text
        if (message.getMessageText() == null || 
            message.getMessageText().trim().isEmpty() || 
            message.getMessageText().length() > 255) {
            return null;
        }

        // Validate that the user exists
        if (accountRepository.findById(message.getPostedBy()).isEmpty()) {
            return null;
        }

        // Save and return the new message
        return messageRepository.save(message);
    }

    // Get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Get message by ID
    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    // Delete a message
    public boolean deleteMessage(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        }
        return false;
    }

    // Update a message
    public Message updateMessage(Integer messageId, Message messageUpdate) {
        Optional<Message> existingMessage = messageRepository.findById(messageId);
        
        // Validate message update
        if (existingMessage.isPresent()) {
            String newText = messageUpdate.getMessageText();
            if (newText != null && !newText.trim().isEmpty() && newText.length() <= 255) {
                Message message = existingMessage.get();
                message.setMessageText(newText);
                return messageRepository.save(message);
            }
        }
        
        return null;
    }

    // Get messages by user
    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
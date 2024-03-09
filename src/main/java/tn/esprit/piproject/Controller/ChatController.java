package tn.esprit.piproject.Controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.ChatMessage;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.UserRepository;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

        @Autowired
        private IProjectService iProjectService;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private AutoIncrementUtil autoIncrementUtil;

        @MessageMapping("/chat")
        @SendTo("/topic/messages")
        public ChatMessage handleMessage(ChatMessage message) {
            User sender = userRepository.findById(message.getRecipient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

            User recipient = userRepository.findById(message.getSender().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

            message.setSender(sender);
            message.setRecipient(recipient);

            int id = autoIncrementUtil.getNextSequence("votre_sequence");
            message.setId(id);

            return iProjectService.saveMessage(message);
        }

    @GetMapping("/messages/{supervisorId}/{studentId}")
    public List<ChatMessage> getMessagesBetweenSupervisorAndStudent(@PathVariable int supervisorId, @PathVariable int studentId) {
        return iProjectService.getMessagesBetweenSupervisorAndStudent(supervisorId, studentId);
    }
}

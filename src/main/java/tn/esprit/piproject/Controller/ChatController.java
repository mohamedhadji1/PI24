package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.ChatMessage;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.UserRepository;
import tn.esprit.piproject.Services.IProjectService;
import tn.esprit.piproject.models.ChatMessageModel;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {


    private final IProjectService iProjectService;

    private final UserRepository userRepository;

    private final AutoIncrementUtil autoIncrementUtil;

    private final SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/add_new_chat")
//    @SendTo("/chat/added_chat")
    public ChatMessage handleMessage(ChatMessageModel message) {
        System.out.println(message);
        User sender = userRepository.findById(message.senderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        User recipient = userRepository.findById(message.receiverId())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);
        chatMessage.setText(message.text());
        int id = autoIncrementUtil.getNextSequence("votre_sequence");
        chatMessage.setId(id);
        var savedMessage = iProjectService.saveMessage(chatMessage);
        simpMessagingTemplate.convertAndSend("/topic/added_chat", savedMessage);

        return savedMessage;
    }
}

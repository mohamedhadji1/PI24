package tn.esprit.piproject.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {

    @MessageMapping("/notification")
    @SendTo("/topic/notifications")
    public String handleNotification(String message) {
        return "Received notification: " + message;
    }
}
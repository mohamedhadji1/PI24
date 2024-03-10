package tn.esprit.piproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.ChatMessage;
import tn.esprit.piproject.Entities.Notification;
import tn.esprit.piproject.Services.IProjectService;

@Controller

public class NotificationController {

        @Autowired
        private IProjectService iProjectService;
        @Autowired
        private AutoIncrementUtil autoIncrementUtil;

        @MessageMapping("/notification")
        @SendTo("/topic/notification")
        public Notification sendNotification(Notification notification) {

            Notification notification1 = new Notification();
            notification1.setSender(notification.getSender());
            notification1.setDescription(notification.getDescription());
            int id = autoIncrementUtil.getNextSequence("votre_sequence");
            notification1.setId(id);
            var notif = iProjectService.createNotification(notification);
            return notif;
        }
    }


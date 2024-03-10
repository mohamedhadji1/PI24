import { Component, OnInit } from '@angular/core';
import { WebSocketService } from 'src/app/services/WebSocket.service';

@Component({
  selector: 'notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent{

  notifications: any[] = [];
  public notificationcount:number=0;
  constructor(private notificationService: WebSocketService) { }
  ngOnInit(): void {
    this.notificationService.getNotifications(notif => {
      this.notifications.push(notif);
    });

  }

}

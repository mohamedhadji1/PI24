import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private socket$: WebSocketSubject<any>;

  constructor() {
    this.socket$ = webSocket('ws://localhost:8081/ws/notification');
  }

  sendNotification(notification: any): void {
    this.socket$.next(notification);
  }

  receiveNotifications(): WebSocketSubject<any> {
    return this.socket$;
  }
}
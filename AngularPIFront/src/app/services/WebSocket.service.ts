import { Injectable, OnDestroy } from '@angular/core';
import { notification } from '../core/notification';
import { CompatClient, Stomp, StompSubscription } from '@stomp/stompjs';
import { WebSocketSubject, webSocket } from 'rxjs/webSocket';

export type ListenerCallBack = (message: Notification) => void;


@Injectable({
  providedIn: "root",
})
export class WebSocketService implements OnDestroy{

  private connection: CompatClient | undefined = undefined;

  private subscription: StompSubscription | undefined;

  constructor() {
    this.connection = Stomp.client('ws://localhost:8081/websocket');
    this.connection.connect({}, () => {});
  }

  getNotifications(fun: ListenerCallBack) {
    if (this.connection) {
      this.connection.connect({}, () => {
        this.subscription = this.connection!.subscribe('/topic/notification', message => fun(JSON.parse(message.body)));
      });
    }
  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}

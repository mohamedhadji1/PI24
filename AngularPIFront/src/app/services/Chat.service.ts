import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Observable } from 'rxjs';
import { ChatMessage } from '../core/ChatMessage';


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private socket$: WebSocketSubject<ChatMessage>;

  constructor() {
    this.socket$ = webSocket('ws://localhost:8081/ws'); 
  }

  // Method to send a message to the WebSocket server
  sendMessage(message: ChatMessage): void {
    this.socket$.next(message);
  }

  // Method to receive messages from the WebSocket server
  receiveMessage(): Observable<ChatMessage> {
    return this.socket$;
  }
}

import { Component, OnInit } from '@angular/core';
import { ChatMessage } from 'src/app/core/ChatMessage';
import { ChatService } from 'src/app/services/Chat.service';

@Component({
  selector: 'app-Chat',
  templateUrl: './Chat.component.html',
  styleUrls: ['./Chat.component.css']
})
export class ChatComponent implements OnInit {
  chats: ChatMessage[] = [];

  constructor(private chatservice:ChatService) {}
  ngOnInit(): void {
    this.chatservice.listen(chaters => {
      this.chats.push(chaters);
    });
  }
  add(message: string, timestamp: Date,senderId: number,recipientId: number): void {
    const chatmem: ChatMessage = {
      message: message,
      timestamp: timestamp,
      senderId:senderId,
      recipientId:recipientId
    };
    this.chatservice.send(chatmem);
  }

}

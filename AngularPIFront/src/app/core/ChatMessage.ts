export interface ChatMessage {
  id: number;
  message: string;
  timestamp: Date;
  senderId: number;
  recipientId: number;
}

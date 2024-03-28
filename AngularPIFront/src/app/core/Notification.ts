import { User } from "./User";

export enum NotificationStatus {
    READ = 'read',
    UNREAD = 'unread'
  }
  
  export interface Notification {
    id: number;
    sender: User;
    recipient: User;
    message: string;
    timestamp: Date;
    status: NotificationStatus;
  }
export interface Notification {
  id: number;
  notificationType: NotificationType;
  message: string
  timestamp: Date
}

export interface Notifications {
  beehives: Notification[];
}

export enum NotificationType {
  INFO,
  WARNING,
  CRITICAL
}

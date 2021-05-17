export interface Beehive {
  id: string;
  weight: number;
  temperature: number;
  populationNr: number;
  activityType: Activity
}

export interface Beehives {
  beehives: Beehive[];
}

export enum Activity {
  SLOW,
  NORMAL,
  HYPERACTIVE
}

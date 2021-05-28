export interface Beehive {
  id: number;
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

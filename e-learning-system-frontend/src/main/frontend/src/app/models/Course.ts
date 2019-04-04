import {Topic} from "./Topic";
export class Course {
  id: number;
  title: string;
  description: string;
  professorId: number;
  startDate: Date;
  endDate: Date;
  topics: Topic[];
}

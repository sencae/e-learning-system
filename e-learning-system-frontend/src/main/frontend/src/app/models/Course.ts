import {Topic} from "./Topic";
import {Test} from "./Test";

export class Course {
  id: number;
  title: string;
  description: string;
  professorId: number;
  startDate: Date;
  endDate: Date;
  topics: Topic[];
  test: Test;
}

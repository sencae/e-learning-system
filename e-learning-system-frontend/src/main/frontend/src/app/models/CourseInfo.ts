import {Topic} from "./Topic";
import {Test} from "./Test";

export class CourseInfo {
  id: number;
  title: string;
  description: string;
  professorId: number;
  startDate: Date;
  endDate: Date;
  topics: Topic[];
  author: boolean;
  started: boolean;
  join: boolean;
  test: Test;
}

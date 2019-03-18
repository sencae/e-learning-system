import {Resource} from './Resource'
export class Course {
  id: number;
  title: string;
  description: string;
  professorId: number;
  resources: Resource[];
}

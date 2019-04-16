import {Answer} from "./Answer";

export class Question {
  id: number;
  question: string;
  answers: Answer[];

  constructor(question: string, answers: Answer[]) {
    this.question = question;
    this.answers = answers;
  }
}

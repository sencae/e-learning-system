export class Answer {
  id: number;
  answer: string;
  correctAnswer: boolean;
  parentQuestion: number;

  constructor(answer: string) {
    this.answer = answer;
    this.correctAnswer = false;
  }
}

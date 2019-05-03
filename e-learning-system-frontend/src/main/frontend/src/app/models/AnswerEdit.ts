export class AnswerEdit {
  id?: number;
  answer?: string;
  correctAnswer?: boolean;
  parentQuestion?: number;

  constructor(params: AnswerEdit = {} as AnswerEdit) {

    let {
      id = null,
      answer = '',
      parentQuestion = null,
      correctAnswer = false
    } = params;

    this.id = id;
    this.answer = answer;
    this.correctAnswer = correctAnswer;
    this.parentQuestion = parentQuestion;
  }

}

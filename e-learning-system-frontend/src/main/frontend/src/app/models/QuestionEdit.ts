import {AnswerEdit} from "./AnswerEdit";

export class QuestionEdit {
  id?: number;
  question?: string;
  answers?: AnswerEdit[];
  parentTest?: number;
  checkboxType?: boolean;

  constructor(params: QuestionEdit = {} as QuestionEdit) {

    let {
      id = null,
      question = '',
      answers = [new AnswerEdit({parentQuestion: id, answer: ''})],
      parentTest = null,
      checkboxType = false
    } = params;

    this.id = id;
    this.question = question;
    this.answers = answers;
    this.parentTest = parentTest;
    this.checkboxType = checkboxType;
  }
}

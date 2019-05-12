import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TestService} from "../../services/course/test.service";
import {AlertService} from "../../services/alert.service";
import {RxwebValidators} from "@rxweb/reactive-form-validators";
import {QuestionEdit} from "../../models/QuestionEdit";
import {AnswerEdit} from "../../models/AnswerEdit";

@Component({
  selector: 'app-edit-test',
  templateUrl: './edit-test.component.html',
  styleUrls: ['./edit-test.component.css']
})
export class EditTestComponent implements OnInit {
  testForm: FormGroup;
  loading = false;
  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private testService: TestService,
              private alertService: AlertService,
              private router: Router
  ) {
  }



  ngOnInit() {
    this.getTest();

  }

  createQuestionGroup(question: QuestionEdit) {
    return this.formBuilder.group({
      ...question,
      ...{
        question: [question.question, Validators.required],
        answers: this.formBuilder.array(question.answers.map(elem =>
          this.createAnswerGroup(elem)))
      }
    });
  }

  createAnswerGroup(answer: AnswerEdit) {
    return this.formBuilder.group({
      ...answer,
      ...{
        answer: [answer.answer, [Validators.required, RxwebValidators.unique()]],
        correctAnswer: [answer.correctAnswer]
      }
    })
  }

  onSelect(question: any, answer: any) {
    question.get('answers').controls.forEach((x) => {
      if (x.value.answer != answer.value.answer) {
        x.controls.correctAnswer.reset(false);
      }
    })
  }

  onSubmit() {
    if (this.testForm.invalid)
      return;
    this.loading = true;
    const id = +this.route.snapshot.paramMap.get('id');
    this.testService.save(this.testForm.value, id).subscribe(data => {
        this.alertService.success('You successfully update test', true);
        this.router.navigate(['/course/' + id + '/edit']);
      },
      error1 => {
        this.alertService.error('Failed to update test');
        this.loading = false;
      })
  }

  getTest() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.testService.getTestEdit(id).subscribe(data => {
        this.testForm = this.formBuilder.group({
          testName: [data.testName, Validators.required],
          questions: this.formBuilder.array(data.questions.map(elem =>
            this.createQuestionGroup(elem)))
        })
      },
      error1 => {
        this.alertService.error(error1, true);
        this.router.navigate(['/course/' + id]);
      });

  }

  answers(index: number) {
    return this.questions.at(index).get('answers') as FormArray;
  }

  get questions() {
    return this.testForm.get('questions') as FormArray;
  }

  addAnswer(index: number) {
    this.answers(index).push(this.createAnswerGroup(new AnswerEdit({parentQuestion: this.questions.at(index).value.id})));
  }

  popAnswer(answer: any, question: number) {
    this.answers(question).removeAt(this.answers(question).value.findIndex(answ => answ.answer == answer.value.answer))
  }

  deleteQuestion(questionID: number) {
    this.questions.removeAt(questionID);
  }
  addQuestion() {
    this.questions.push(this.createQuestionGroup(new QuestionEdit({parentTest: this.testForm.value.id})));
  }
}

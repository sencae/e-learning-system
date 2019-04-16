import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TestService} from "../../services/course/test.service";
import {AlertService} from "../../services/alert.service";
import {Question} from "../../models/Question";
import {Answer} from "../../models/Answer";
import {RxwebValidators} from "@rxweb/reactive-form-validators";

@Component({
  selector: 'app-edit-test',
  templateUrl: './edit-test.component.html',
  styleUrls: ['./edit-test.component.css']
})
export class EditTestComponent implements OnInit {
  testForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private testService: TestService,
              private alertService: AlertService,
              private router: Router
  ) {
  }

  get questions() {
    return this.testForm.get('questions') as FormArray;
  }

  ngOnInit() {
    this.getTest();

  }

  createQuestionGroup(question: Question) {
    return this.formBuilder.group({
      ...question,
      ...{
        question: [question.question, Validators.required],
        answers: this.formBuilder.array(question.answers.map(elem =>
          this.createAnswerGroup(elem)))
      }
    });
  }

  createAnswerGroup(answer: Answer) {
    return this.formBuilder.group({
      ...answer,
      ...{
        answer: [answer.answer, [Validators.required, RxwebValidators.unique()]],
        correctAnswer: [answer.correctAnswer, Validators.required]
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
    const id = +this.route.snapshot.paramMap.get('id');
    this.testService.save(this.testForm.value, id).subscribe()
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

  addAnswer(index: number) {
    this.answers(index).push(this.createAnswerGroup(new Answer("")));
  }

  addQuestion() {
    this.questions.push(this.createQuestionGroup(new Question("", [new Answer("")])));
  }
}

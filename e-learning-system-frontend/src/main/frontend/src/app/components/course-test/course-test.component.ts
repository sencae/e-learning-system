import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TestService} from "../../services/course/test.service";
import {Test} from "../../models/Test";
import {AlertService} from "../../services/alert.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Question} from "../../models/Question";
import {Answer} from "../../models/Answer";

@Component({
  selector: 'app-course-test',
  templateUrl: './course-test.component.html',
  styleUrls: ['./course-test.component.css']
})
export class CourseTestComponent implements OnInit {
  private test: Test;
  private testForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private testService: TestService,
              private router: Router,
              private alertService: AlertService,
              private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.getTest();
  }

  completeTest() {

    const id = +this.route.snapshot.paramMap.get('id');
    this.testService.completeTest(this.testForm.value, id).subscribe(
      data => this.alertService.success(data.toString()));
  }

  getTest() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.testService.getTest(id).subscribe(data => {
        this.test = data;
        this.testForm = this.formBuilder.group({
          testName: [data.testName],
          questions: this.formBuilder.array(data.questions.map(elem =>
            this.createQuestionGroup(elem)))
        })
      },
      error1 => {
        this.alertService.error(error1.message, true);
        this.router.navigate(['/course/' + id]);
      });
  }

  createQuestionGroup(question: Question) {
    return this.formBuilder.group({
      ...question,
      ...{
        question: [question.question],
        answers: this.formBuilder.array(question.answers.map(elem =>
          this.createAnswerGroup(elem)))
      }
    });
  }

  createAnswerGroup(answer: Answer) {
    return this.formBuilder.group({
      ...answer,
      ...{
        answer: [answer.answer],
        id: [answer.id]
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
}

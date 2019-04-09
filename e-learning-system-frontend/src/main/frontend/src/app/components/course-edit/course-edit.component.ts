import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Resource} from "../../models/Resource";
import {FileExchangeService} from "../../services/fileExchange.service";
import {TopicService} from "../../services/course/topic.service";
import {CreateTopic} from "../../models/CreateTopic";
import {FormControl} from "@angular/forms";
import {CreateTest} from "../../models/CreateTest";
import {TestService} from "../../services/course/test.service";
import {Topic} from "../../models/Topic";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {
  course: Course;
  openForm = false;
  openFormTest = false;
  topicTtl: number;
  testTitle = new FormControl('');
  private createTopic: CreateTopic;
  topicTitle = new FormControl('');
  private createTest: CreateTest;

  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private fileEx: FileExchangeService,
              private topicService: TopicService,
              private router: Router,
              private testService: TestService) {
  }

  ngOnInit() {
    this.getCourseInfo()
  }

  getCourseInfo() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course => {
        this.course = course;
        if(course.author == false)
          this.router.navigate(['404']);
      }
    )
  }

  info(title:number) {
    this.topicTtl = title;
  }

  onClickOpenForm() {
    this.openForm = true;
    return this.openForm;
  }

  onClickOpenFormQuiz() {
    this.openFormTest = true;
    return this.openFormTest;
  }

  saveTest() {
    this.createTest = new CreateTest(
      this.testTitle.value,
      +this.route.snapshot.paramMap.get('id'));
    this.testService.saveTest(this.createTest).subscribe(data => {
        this.course.test = data;
        this.openFormTest = false;
      }, error => console.log(error)
    )
  }
  saveTopic() {
    this.createTopic = new CreateTopic(
      this.topicTitle.value,
      +this.route.snapshot.paramMap.get('id'));
    this.topicService.saveTopic(this.createTopic).subscribe(data => {
        this.course.topics.push(data);
      this.openForm = false;
      }, error => console.log(error)
    )
  }

  deleteTopic(topic: Topic) {
    this.topicService.deleteTopic(topic).subscribe(data => {
      console.log(data);
      this.course.topics = this.course.topics.filter(h => h !== topic);
    });
  }

  deleteResource(resource: Resource) {
    this.fileEx.deleteResource(resource.url).subscribe(data => {
        console.log(data);
        window.location.reload();
      },
      error => console.log(error));
  }

}

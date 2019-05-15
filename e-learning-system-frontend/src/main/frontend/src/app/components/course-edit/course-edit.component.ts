import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Resource} from "../../models/Resource";
import {FileExchangeService} from "../../services/fileExchange.service";
import {TopicService} from "../../services/course/topic.service";
import {CreateTopic} from "../../models/CreateTopic";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CreateTest} from "../../models/CreateTest";
import {TestService} from "../../services/course/test.service";
import {Topic} from "../../models/Topic";
import {AlertService} from "../../services/alert.service";
import {CourseInfo} from "../../models/CourseInfo";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  courseEditForm: FormGroup;
  openFormTest = false;
  createTopic: CreateTopic;
  topicTitle = new FormControl('');
  createTest: CreateTest;
  testTitle = new FormControl('');
  submitted = false;
  openForm = false;
  topicTtl: number;
  checkbox = false;
  course: CourseInfo;
  show = false;
  url = '';
  loading = false;
  readonly id = +this.route.snapshot.paramMap.get('id');

  constructor(private courseService: CourseService,
              private topicService: TopicService,
              private alertService: AlertService,
              private testService: TestService,
              private formBuilder: FormBuilder,
              private fileEx: FileExchangeService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  get f() {
    return this.courseEditForm.controls;
  }

  ngOnInit() {
    this.getCourseInfo();
  }

  getCourseInfo() {
    this.courseService.getCourse(this.id).subscribe(
      course => {
        this.course = course;
        if (course.author == false)
          this.router.navigate(['404']);
        this.courseEditForm = this.formBuilder.group({
          id: [''],
          title: [course.title, Validators.required],
          description: [course.description],
          startDate: [course.startDate.toString().substring(0, 16), Validators.required],
          endDate: [this.course.endDate.toString().substring(0, 16), Validators.required],
          file: [null],
          url: [course.url]
        })
      },
      error1 => {
        this.router.navigate(['404']);
      }
    )
  }

  info(title: number) {
    this.topicTtl = title;
  }

  onClickOpenForm() {
    this.openForm = true;
    return this.openForm;
  }

  onClickOpenFormTest() {
    this.openFormTest = true;
    return this.openFormTest;
  }

  deleteTest() {
    this.testService.deleteTest(this.id).subscribe(data => this.course.test = null);
  }
  saveTest() {
    this.createTest = new CreateTest(
      this.testTitle.value,
      this.id);
    this.testService.saveTest(this.createTest).subscribe(data => {
      this.course.test = data.testName;
        this.openFormTest = false;
      }, error => this.alertService.error("Failed to create test", false)
    )
  }

  saveTopic() {
    this.createTopic = new CreateTopic(
      this.topicTitle.value,
      this.id);
    this.topicService.saveTopic(this.createTopic).subscribe(data => {
        this.course.topics.push(data);
      this.openForm = false;
      }, error => console.log(error)
    )
  }

  deleteTopic(topic: Topic) {
    this.topicService.deleteTopic(topic).subscribe(data => {
      this.course.topics = this.course.topics.filter(h => h !== topic);
      },
      error => this.alertService.error("Failed to delete topic", false));
  }

  deleteResource(resource: Resource, index: number) {
    this.fileEx.deleteResource(resource.url).subscribe(data => {
        this.course.topics[index].courseResources.filter(elem => elem !== resource);
      },
      error => this.alertService.error("Failed to delete resource", false)
    )
  }

  deleteCheck(values: any) {
    this.checkbox = values.currentTarget.checked;
  }

  deleteFile() {
    this.fileEx.deleteCourseImage(this.course.url).subscribe(data => {
        this.course.url = null;
        this.courseEditForm.value.url = null;
      },
      error => {
        console.log("Failed to delete profile image")
      }
    )
  }

  selectFile(event) {
    const reader = new FileReader();
    this.courseEditForm.patchValue({file: event.target.files[0]});

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = (event: any) => {
        this.url = event.target.result;
        this.show = true;
      }
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.courseEditForm.invalid) {
      return;
    }
    this.loading = true;
    if (this.checkbox) {
      this.deleteFile();
    }
    this.courseEditForm.patchValue({id: this.id});
    if (this.courseEditForm.value.file !== null) {
      this.fileEx.uploadCourseImg(this.courseEditForm.value.file, this.id)
        .subscribe(data => {
            this.courseEditForm.value.url = data;
            this.courseService.updateCourse(this.courseEditForm.value).subscribe(data => {
                this.alertService.success('You successfully update course', true);
                this.router.navigate(['course/', this.id]);
              },
              error => {
                this.alertService.error('error', false);
                this.loading = false;
              }
            );
          }
        )
    }
    else {
      this.courseService.updateCourse(this.courseEditForm.value).subscribe(data => {
          this.alertService.success('You successfully update course', true);
          this.router.navigate(['course/', this.id])
        },
        error => {
          this.alertService.error('error', false);
        }
      );
    }
  }
}

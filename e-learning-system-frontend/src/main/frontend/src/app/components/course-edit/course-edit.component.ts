import {Component,  OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute} from "@angular/router";
import {Resource} from "../../models/Resource";
import {FileExchangeService} from "../../services/fileExchange.service";
import {TopicService} from "../../services/course/topic.service";
import {CreateTopic} from "../../models/CreateTopic";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {
  filesToUpload: Array<File> = [];
  course: Course;
  selectedFiles: FileList;
  openForm = false;
  private createTopic: CreateTopic;
  topicTitle = new FormControl('');

  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private fileEx: FileExchangeService,
              private topicService: TopicService) {
  }

  ngOnInit() {
    this.getCourseInfo()
  }

  getCourseInfo() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course => {
        this.course = course;
        console.log(this.course);
      }
    )
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
  }

  onClickOpenForm() {
    this.openForm = true;
    return this.openForm;
  }

  saveTopic() {
    this.createTopic = new CreateTopic(
      this.topicTitle.value,
      +this.route.snapshot.paramMap.get('id'));
    this.topicService.saveTopic(this.createTopic).subscribe(data =>{
    this.course.topics.push(data);
    },error =>console.log(error)
    )
  }

  upload() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.fileEx.uploadResourceFiles(this.filesToUpload, id).subscribe(
      data => {
        console.log(data);
        window.location.reload();
      },
      error => console.log(error)
    );
  }

  deleteResource(resource: Resource) {
    this.fileEx.deleteResource(resource.url).subscribe(data => {
        console.log(data);
        window.location.reload();
      },
      error => console.log(error));
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
}

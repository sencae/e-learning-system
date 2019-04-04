import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
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
  course: Course;
  selectedFiles: FileList;
  openForm = false;
  topicTtl: number;
  private createTopic: CreateTopic;
  topicTitle = new FormControl('');

  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private fileEx: FileExchangeService,
              private topicService: TopicService,
              private router: Router) {
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

  saveTopic() {
    this.createTopic = new CreateTopic(
      this.topicTitle.value,
      +this.route.snapshot.paramMap.get('id'));
    this.topicService.saveTopic(this.createTopic).subscribe(data => {
        this.course.topics.push(data);
      }, error => console.log(error)
    )
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

import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../models/Topic";

@Component({
  selector: 'app-course-resources',
  templateUrl: './course-resources.component.html',
  styleUrls: ['./course-resources.component.css']
})
export class CourseResourcesComponent implements OnInit {
  @Input('topics') topics: Topic[];
  @Input('test') test: string;

  constructor() {
  }

  ngOnInit() {
  }

}

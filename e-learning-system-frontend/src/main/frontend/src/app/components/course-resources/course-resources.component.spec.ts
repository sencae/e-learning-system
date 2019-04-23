import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CourseResourcesComponent} from './course-resourses.component';

describe('CourseResoursesComponent', () => {
  let component: CourseResourcesComponent;
  let fixture: ComponentFixture<CourseResourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CourseResourcesComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

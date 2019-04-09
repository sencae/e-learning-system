export class CreateTest {
  testName: string;
  courseId: number;

  constructor(title: string, courseId: number) {
    this.testName = title;
    this.courseId = courseId;
  }
}

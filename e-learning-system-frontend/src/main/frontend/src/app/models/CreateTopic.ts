export class CreateTopic {
  title:string;
  courseId:number;
  constructor(title: string, courseId: number) {
    this.title = title;
    this.courseId = courseId;
  }
}

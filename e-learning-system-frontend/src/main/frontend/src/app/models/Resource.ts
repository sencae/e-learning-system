export class Resource {
  title?: string;
  topicId?: number;
  url?: string;

  constructor(params: Resource = {} as Resource) {

    let {
      title = '',
      topicId = 0,
      url = ''
    } = params;

    this.title = title;
    this.topicId = topicId;
    this.url = url;
  }
}

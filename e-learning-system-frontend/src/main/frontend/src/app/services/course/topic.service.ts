import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {CreateTopic} from "../../models/CreateTopic";
import {Topic} from "../../models/Topic";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private createTopicUrl = '/api/createTopic';
  private deleteTopicUrl = '/api/deleteTopic';
  constructor(private http: HttpClient) {
  }

  saveTopic(createTopic: CreateTopic):Observable<Topic> {
    return this.http.post<Topic>(this.createTopicUrl, createTopic)
  }

  deleteTopic(topic: Topic) {
    return this.http.post(this.deleteTopicUrl, topic);
  }
}

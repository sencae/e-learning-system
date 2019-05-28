import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FileExchangeService} from "../../services/fileExchange.service";
import {AlertService} from "../../services/alert.service";
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-download-resource',
  templateUrl: './download-resource.component.html',
  styleUrls: ['./download-resource.component.css']
})
export class DownloadResourceComponent implements OnInit {
  @Input() topic: number;
  filesToUpload: Array<File> = [];
  loading = false;
  linkGroup: FormGroup;
  links: FormArray;
  constructor(private route: ActivatedRoute,
              private fileEx: FileExchangeService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
  }
  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
  }
  addLink(){
    if(!this.linkGroup)
      this.linkGroup = this.formBuilder.group({
        links: this.formBuilder.array([this.createLink()])
      });
    else {
      this.links = this.linkGroup.get('links') as FormArray;
      this.links.push(this.createLink());
    }
  }
  createLink():FormGroup{
    return this.formBuilder.group({
      topicId: [this.topic],
      url: [''],
      title:['']
    })
  }
  upload() {
    this.loading = true;
    this.fileEx.uploadResourceFiles(this.filesToUpload,this.topic).subscribe(
      data => {
        if(this.linkGroup){
        this.fileEx.uploadResourceLinks(this.linkGroup.value,this.topic).subscribe(data=>{

          this.alertService.success('You successfully uploaded resources!',true);
          document.getElementById('closeModal').click();
          this.loading = false;
          this.linkGroup.reset()
        },error1 => {
          this.alertService.error('FAIL to upload files!', false);
        this.loading = null;
        document.getElementById('closeModal').click();
        this.linkGroup.reset()
        })
        }else {
          this.alertService.success('You successfully uploaded resources!',true);
          document.getElementById('closeModal').click();
          this.loading = false;
        }},
      error => {
        this.alertService.error('FAIL to upload files!', false);
        this.loading = null;
        document.getElementById('closeModal').click();
        this.linkGroup.reset()
      }
    );
  }


}

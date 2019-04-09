import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FileExchangeService} from "../../services/fileExchange.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-download-resource',
  templateUrl: './download-resource.component.html',
  styleUrls: ['./download-resource.component.css']
})
export class DownloadResourceComponent implements OnInit {
  @Input() topic: number;
  filesToUpload: Array<File> = [];
  constructor(private route: ActivatedRoute,
              private fileEx: FileExchangeService,
              private alertService: AlertService) { }

  ngOnInit() {
  }
  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
  }
  upload() {
    this.fileEx.uploadResourceFiles(this.filesToUpload,this.topic).subscribe(
      data => {
        this.alertService.success('You successfully uploaded '+ this.filesToUpload.length+  ' files!',true);
        },
      error => this.alertService.error('FAIL to upload files!',false)
    );
  }


}

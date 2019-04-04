import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadResourceComponent } from './download-resource.component';

describe('DownloadResourceComponent', () => {
  let component: DownloadResourceComponent;
  let fixture: ComponentFixture<DownloadResourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DownloadResourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DownloadResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

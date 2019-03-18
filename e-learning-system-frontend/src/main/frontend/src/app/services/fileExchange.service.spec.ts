import { TestBed } from '@angular/core/testing';

import { FileExchangeService } from './fileExchange.service';

describe('FileExchangeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileExchangeService = TestBed.get(FileExchangeService);
    expect(service).toBeTruthy();
  });
});

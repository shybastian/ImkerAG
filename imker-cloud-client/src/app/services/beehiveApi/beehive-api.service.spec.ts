import { TestBed } from '@angular/core/testing';

import { BeehiveApiService } from './beehive-api.service';

describe('BeehiveApiService', () => {
  let service: BeehiveApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeehiveApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

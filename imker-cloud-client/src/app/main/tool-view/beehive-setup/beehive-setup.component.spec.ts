import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeehiveSetupComponent } from './beehive-setup.component';

describe('BeehiveSetupComponent', () => {
  let component: BeehiveSetupComponent;
  let fixture: ComponentFixture<BeehiveSetupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BeehiveSetupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeehiveSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

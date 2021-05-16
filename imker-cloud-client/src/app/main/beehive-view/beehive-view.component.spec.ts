import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeehiveViewComponent } from './beehive-view.component';

describe('BeehiveViewComponent', () => {
  let component: BeehiveViewComponent;
  let fixture: ComponentFixture<BeehiveViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BeehiveViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeehiveViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

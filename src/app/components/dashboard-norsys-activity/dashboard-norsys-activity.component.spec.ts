import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardNorsysActivityComponent } from './dashboard-norsys-activity.component';

describe('DashboardNorsysActivityComponent', () => {
  let component: DashboardNorsysActivityComponent;
  let fixture: ComponentFixture<DashboardNorsysActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardNorsysActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardNorsysActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

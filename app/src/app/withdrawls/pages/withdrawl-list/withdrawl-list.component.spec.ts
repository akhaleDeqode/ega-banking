import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WithdrawlListComponent } from './withdrawl-list.component';

describe('WithdrawlListComponent', () => {
  let component: WithdrawlListComponent;
  let fixture: ComponentFixture<WithdrawlListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WithdrawlListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WithdrawlListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

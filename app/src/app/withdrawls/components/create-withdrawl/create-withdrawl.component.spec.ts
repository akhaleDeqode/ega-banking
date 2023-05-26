import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateWithdrawlComponent } from './create-withdrawl.component';

describe('CreateWithdrawlComponent', () => {
  let component: CreateWithdrawlComponent;
  let fixture: ComponentFixture<CreateWithdrawlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateWithdrawlComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateWithdrawlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

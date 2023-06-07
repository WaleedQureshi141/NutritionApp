import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPageNavComponent } from './user-page-nav.component';

describe('UserPageNavComponent', () => {
  let component: UserPageNavComponent;
  let fixture: ComponentFixture<UserPageNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserPageNavComponent]
    });
    fixture = TestBed.createComponent(UserPageNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

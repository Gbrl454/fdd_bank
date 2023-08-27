import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplateNoAuthComponent } from './template-no-auth.component';

describe('TemplateNoAuthComponent', () => {
  let component: TemplateNoAuthComponent;
  let fixture: ComponentFixture<TemplateNoAuthComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TemplateNoAuthComponent]
    });
    fixture = TestBed.createComponent(TemplateNoAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

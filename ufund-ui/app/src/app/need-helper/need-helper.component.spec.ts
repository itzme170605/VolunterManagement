import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedHelperComponent } from './need-helper.component';

describe('NeedHelperComponent', () => {
  let component: NeedHelperComponent;
  let fixture: ComponentFixture<NeedHelperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NeedHelperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NeedHelperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

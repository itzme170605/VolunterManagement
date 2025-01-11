import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCupboardComponent } from './edit-cupboard.component';

describe('EditCupboardComponent', () => {
  let component: EditCupboardComponent;
  let fixture: ComponentFixture<EditCupboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditCupboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditCupboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

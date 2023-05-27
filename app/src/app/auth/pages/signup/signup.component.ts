import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { EMAIL_REGEX, NAME_REGEX, PASSWORD_REGEX } from 'src/app/core/constants/custom-validataros';
import { Signup } from 'src/app/core/models/auth.model';
import { AuthService } from 'src/app/core/services/auth.service';
import { ToasterService } from 'src/app/core/services/toaster.service';
import { UtilityService } from 'src/app/core/services/utility.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  signupForm!: FormGroup;
  isFormSubmitted: boolean = false;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _formBuilder: FormBuilder,
    private _authService: AuthService,
    private _router: Router,
    private _toasterService: ToasterService,
    public _utility: UtilityService
  ) { }

  ngOnInit(): void {
    this.signupForm = this._formBuilder.group({
      firstName: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(50), Validators.pattern(NAME_REGEX)]],
      lastName: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(50), Validators.pattern(NAME_REGEX)]],
      email: [null, [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      password: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(16), Validators.pattern(PASSWORD_REGEX)]]
    });
  }

  get FormControl() {
    return this.signupForm.controls;
  }

  submit(): void {
    this.isFormSubmitted = true;
    // console.log(this.signupForm.value);
    if (this.signupForm.valid) {
      let payload: Signup = this.signupForm.value;
      this._authService.signup(payload).pipe(takeUntil(this._unsubscribe$)).subscribe({
        next: (res: any) => {
          console.log(res);
          this._toasterService.success('Success', 'User signed up successfully');
          this._router.navigate(['/auth/login']);
        }
      });
    }
  }

  ngOnDestroy(): void {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }

}

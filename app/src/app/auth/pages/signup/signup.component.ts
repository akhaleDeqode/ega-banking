import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { EMAIL_REGEX, MAX_FIRST_NAME, MAX_PASSWORD, MAX_lAST_NAME, MIN_FIRST_NAME, MIN_PASSWORD, NAME_REGEX, PASSWORD_REGEX } from 'src/app/core/constants/custom-validators';
import { ErrorMessages } from 'src/app/core/constants/error-messages';
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
  errorMessage = new ErrorMessages();
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
      firstName: [null, [Validators.required, Validators.minLength(MIN_FIRST_NAME), Validators.maxLength(MAX_FIRST_NAME), Validators.pattern(NAME_REGEX)]],
      lastName: [null, [Validators.required, Validators.minLength(MIN_FIRST_NAME), Validators.maxLength(MAX_lAST_NAME), Validators.pattern(NAME_REGEX)]],
      email: [null, [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      password: [null, [Validators.required, Validators.minLength(MIN_PASSWORD), Validators.maxLength(MAX_PASSWORD), Validators.pattern(PASSWORD_REGEX)]]
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

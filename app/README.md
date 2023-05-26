# EGA Banking

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.2.6.

## Prerequisites

#### 1. Install [NodeJs](https://nodejs.org/en/download)
#### 2. Check installed NodeJs version
    node -v
#### 3. Install Angular CLI
    npm install -g @angular/cli@latest

### Folder Structure

```
  .
  └── src
      ├── app
      │   ├── auth
                ├── pages
                    ├── login
                    ├── signup
      |   ├── core
                ├── guards
                ├── interceptors
                ├── models
                ├── routes
                ├── services
      │   ├── dashboard
                ├── components
                    ├── current-balance
                    ├── recent-transactions
                ├── pages
                    ├── dashboard
      │   ├── deposits
                ├── components
                    ├── create-deposits
                ├── pages
                    ├── desposit-list
      │   ├── withdrawls
                ├── components
                    ├── create-withdrawls
                ├── pages
                    ├── withdrawls-list
      │   └── shared
                ├── components
                ├── layouts
      └── styles
```

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
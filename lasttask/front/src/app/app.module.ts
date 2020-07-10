import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RouterModule} from '@angular/router';
import { MainWindowComponent } from './components/main-window/main-window.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material/dialog';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from './service/user.service';
import {LinkService} from './service/link.service';
import {TokenInterceptor} from './service/token.interceptor';
import {JwtInterceptor} from './service/jwt.interceptor';
import {RouteModuleModule} from './route-module/route-module.module';
import { RegistrationComponent } from './components/registration/registration.component';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { LinkPageComponent } from './components/link-page/link-page.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ItemLinkComponent } from './components/item-link/item-link.component';
import { CommentComponent } from './components/comment/comment.component';

@NgModule({
  declarations: [
    AppComponent,
    MainWindowComponent,
    RegistrationComponent,
    LinkPageComponent,
    NavigationComponent,
    ItemLinkComponent,
    CommentComponent
  ],
    imports: [
        BrowserModule,
      FormsModule,
      ReactiveFormsModule,
      BrowserAnimationsModule,
      MatDialogModule,
      HttpClientModule,
      NgbModule,
      RouteModuleModule,
      RouterModule
    ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, UserService,LinkService,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },JwtInterceptor,MatSnackBarModule, MatSnackBar],
  bootstrap: [AppComponent]
})
export class AppModule { }

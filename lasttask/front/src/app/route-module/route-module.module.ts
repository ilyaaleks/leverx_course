import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainWindowComponent} from '../components/main-window/main-window.component';
import {RegistrationComponent} from '../components/registration/registration.component';
import {LinkPageComponent} from '../components/link-page/link-page.component';
import {AuthGuard} from '../guard/auth.guard';
import {AboutUserComponent} from '../components/about-user/about-user.component';


const appRoutes: Routes = [
  {path: 'home', component: MainWindowComponent},
  {path: 'user/:id', component: AboutUserComponent, canActivate: [AuthGuard]},
  // {path: 'signout', component: MainComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'story', component: LinkPageComponent, canActivate: [AuthGuard]},
  // {
  //   path: '', redirectTo: '/home', pathMatch: 'full'
  // },
  // {path: 'story/:tag', component: ActivityComponent, canActivate: [AuthGuard]},
  // {
  //   path: '**', component: MainComponent
  // }

];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes, {enableTracing: true, onSameUrlNavigation: 'reload'})
  ]
})
export class RouteModuleModule {
}

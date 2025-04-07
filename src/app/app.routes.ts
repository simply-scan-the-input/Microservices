import { Routes } from '@angular/router';
import { CategoryListComponent } from './categories/category-list.component';
import { CategoryFormComponent } from './categories/category-form/category-form.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
import { CategoryDetailsComponent } from './categories/category-details/category-details.component';


export const routes: Routes = [
  { path: 'categories', component: CategoryListComponent },
  { path: 'categories/new', component: CategoryFormComponent },
  { path: 'categories/edit/:id', component: CategoryEditComponent }, 
  { path: 'categories/details/:id', component: CategoryDetailsComponent },
  { path: '', redirectTo: '/categories', pathMatch: 'full' }

];

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../category.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css'],
})
export class CategoryFormComponent {
  category = { name: '' }; // Model kategorii

  constructor(private categoryService: CategoryService, private router: Router) {}

  onSubmit() {
    this.categoryService.addCategory(this.category).subscribe(() => {
      this.router.navigate(['/categories']); // Powrót do listy kategorii
    });
  }

  onCancel() {
    this.router.navigate(['/categories']); // Powrót do listy kategorii
  }
}


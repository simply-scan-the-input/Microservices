import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../category.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-edit',
  imports: [FormsModule, CommonModule],
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.css'],
})
export class CategoryEditComponent implements OnInit {
  category = { id: '', name: '' }; // Category model

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCategory();
  }

  getCategory(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.categoryService.getCategory(id).subscribe({
        next: (data) => {
          this.category = data;
        },
        error: (error) => {
          console.error('Error fetching category:', error);
        },
      });
    }
  }

  updateCategory(): void {
    this.categoryService.updateCategory(this.category).subscribe({
      next: () => {
        console.log('Category updated successfully');
        this.router.navigate(['/categories']);
      },
      error: (error) => {
        console.error('Error updating category:', error);
      },
    });
  }
}

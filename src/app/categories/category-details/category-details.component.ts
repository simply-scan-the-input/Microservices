import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css'],
})
export class CategoryDetailsComponent implements OnInit {
  category: any = { id: '', name: '' }; // Szczegóły kategorii
  elements: any[] = []; // Lista piosenek

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCategoryDetails();
  }

  getCategoryDetails(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.categoryService.getCategory(id).subscribe({
        next: (data) => {
          this.category = data;
          this.getSongs(id); // Pobranie piosenek
        },
        error: (error) => {
          console.error('Error fetching category details:', error);
        },
      });
    }
  }

  getSongs(albumId: string): void {
    this.categoryService.getSongs(albumId).subscribe({
      next: (data) => {
        this.elements = data;
      },
      error: (error) => {
        console.error('Error fetching songs:', error);
      },
    });
  }

  deleteElement(elementId: string): void {
    this.categoryService.deleteSong(this.category.id, elementId).subscribe({
      next: () => {
        this.elements = this.elements.filter(element => element.id !== elementId);
      },
      error: (error) => {
        console.error('Error deleting element:', error);
      },
    });
  }
}

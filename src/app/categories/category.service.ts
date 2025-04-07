import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/albums'; // Endpoint z gateway


  constructor(private http: HttpClient) {}

  // Pobranie kategorii
  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  addCategory(category: { name: string }): Observable<any> {
    return this.http.post(this.apiUrl, category);
  }

  // Usunięcie kategorii
  deleteCategory(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  getCategory(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  updateCategory(category: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${category.id}`, category);
  }
// Pobranie piosenek z albumu
getSongs(albumId: string): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/${albumId}/songs`);
}

// Usunięcie elementu (piosenki) z albumu
deleteSong(albumId: string, songId: string): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${albumId}/songs/${songId}`);
}
}

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from './model/Todo';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  API_KEY:string = 'YOUR_API_KEY';

  BASE_URL = 'http://localhost:8080';

  GET_ALL_TODOS_URL:string ='/api/todos';
  URI_TODO:string ='/api/todo';
  

  constructor(private httpClient:HttpClient) { }

  getTodos(){
    return this.httpClient.get(`${this.BASE_URL}${this.GET_ALL_TODOS_URL}`);
  }

  createTodo(newTodo:Todo) {
    return this.httpClient.post(`${this.BASE_URL}${this.URI_TODO}`, newTodo);
  }
  
  updateTodo(todo:Todo){
    return this.httpClient.put(`${this.BASE_URL}${this.URI_TODO}/${todo.id}`,todo);
  }

  deleteTodo(todo:Todo){
    return this.httpClient.delete(`${this.BASE_URL}${this.URI_TODO}/${todo.id}`);
  }
}

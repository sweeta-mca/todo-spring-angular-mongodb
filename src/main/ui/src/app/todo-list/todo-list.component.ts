import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ApiService } from '../api.service';
import {Todo} from '../model/Todo';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  todos:Todo[] =[];
  selectedTodo:Todo=new Todo();
  
  constructor(private service:ApiService) { }

  ngOnInit(): void {
    this.getAllTodos();
  }

  updateTodos()
  {
    console.log("create update ");
    
    if(this.selectedTodo.id !== undefined)
    {
      this.updateTodo(this.selectedTodo);
    } 
    else {
      this.service.createTodo(this.selectedTodo).subscribe((data)=>{
        console.log("created")
      });

      this.refresh();
    }
    
    
  }

  getAllTodos():void {
    this.service.getTodos().subscribe((data:any) => {
     this.todos =data;
    });
    console.log("get all todos");
    
  }
  editTodo(todo:Todo){
    this.selectedTodo = todo;
  }
  
  updateTodo(updateTodo:Todo){
    console.log("update");
    this.service.updateTodo(updateTodo).subscribe(data =>{
      this.selectedTodo = new Todo();     
    })
  }

  deleteTodo(todo:Todo){
    console.log("delete");
    this.service.deleteTodo(todo).subscribe(data =>{
      this.refresh();
    })
  }

  refresh()
  {
    this.getAllTodos();
  }

}

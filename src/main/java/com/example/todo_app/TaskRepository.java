package com.example.todo_app;

import org.springframework.data.repository.CrudRepository;

// CrudRepositoryを継承することで、タスクのCRUD処理（Create, Read, Update, Delete）の
// 基本的なメソッド（save, findAll, deleteなど）を自動で使えるようになります。
// <Task, Long> は、このリポジトリが扱うデータ型（Task）とIDの型（Long）を示します。
public interface TaskRepository extends CrudRepository<Task, Long> {
    
}
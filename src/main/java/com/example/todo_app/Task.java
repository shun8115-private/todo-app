package com.example.todo_app;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class Task {

 // データベースの棚（カラム）に対応する変数（フィールド）
 @Id // このフィールドがデータベースの主キー（ID）であることを示す
 private Long id;  
 @NotBlank(message = "タスク内容は必須です。")
 private String content;       // タスクの内容
 private boolean isDone;       // 完了状態 (true/false)
 
 private LocalDate deadline;   // 期限日 (日付型)
 private int priority;         // 優先度 (数値型: 1=高, 2=中, 3=低)
 
 

 // コンストラクタ（新しいタスクを作成するときに使う設計図）
 public Task(String content, boolean isDone, LocalDate deadline, int priority) {
     this.content = content;
     this.isDone = isDone;
     this.deadline = deadline; 
     this.priority = priority; 
 }
 
 
 // ゲッターとセッター（データの取得と設定を行うメソッド）
 public Long getId() {
     return id;
 }

 public String getContent() {
     return content;
 }

 public void setContent(String content) {
     this.content = content;
 }

 public boolean isDone() {
     return isDone;
 }

 public void setDone(boolean done) {
     isDone = done;
 }
 
 public LocalDate getDeadline() {
	    return deadline;
	}

 public void setDeadline(LocalDate deadline) {
	 this.deadline = deadline;
	}

 public int getPriority() {
	 return priority;
	}

 public void setPriority(int priority) {
	 this.priority = priority;
	}

 // デフォルトコンストラクタ（Spring Data JDBCが内部で利用）
 public Task() {
 }
}

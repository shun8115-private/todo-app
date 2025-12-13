package com.example.todo_app;

import org.springframework.data.repository.CrudRepository;

// CrudRepositoryを継承することで、タスクのCRUD処理（Create, Read, Update, Delete）の
// 基本的なメソッド（save, findAll, deleteなど）を自動で使えるようになります。
// <Task, Long> は、このリポジトリが扱うデータ型（Task）とIDの型（Long）を示します。
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	// contentフィールドに、指定された文字列（likeContent）を含むタスクを検索する
    // "Content" + "Containing" の組み合わせで、自動的に SQL の LIKE 検索が生成されます
    Iterable<Task> findByContentContaining(String likeContent);
    
    // 優先度（昇順）と期限日（昇順）で並び替えるメソッド
    Iterable<Task> findAllByOrderByPriorityAscDeadlineAsc();
    
    //未完了 (isDone=false) のタスクを、ソート順で取得する ★
    // ByIsDoneFalse の部分で、isDoneがfalseのものだけをフィルタリングしています。
    Iterable<Task> findAllByIsDoneFalseOrderByPriorityAscDeadlineAsc();
}
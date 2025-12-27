package com.example.todo_app;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private final TaskRepository repository;
	
	// コンストラクタ（Springが自動でTaskRepositoryを注入する）
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    /**
     * キーワードの有無に応じて、検索または全件（ソート・フィルタリング済み）を取得する
     */
    public Iterable<Task> findTasks(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            // キーワードがある場合: 検索メソッド
            return repository.findByContentContaining(keyword);
        } else {
            // キーワードがない場合: 未完了タスクをソートして取得
            return repository.findAllByIsDoneFalseOrderByPriorityAscDeadlineAsc();
        }
    }
    
    // 新規タスクを追加する
    public void add(Task task) {
        repository.save(task);
    }
    
    // タスクの完了状態を切り替える
    public void toggleDone(Long id) {
        Task task = repository.findById(id).orElseThrow();
        task.setDone(!task.isDone());
        repository.save(task);
    }
    
    // タスクを削除する
    public void delete(Long id) {
        repository.deleteById(id);
    }

}

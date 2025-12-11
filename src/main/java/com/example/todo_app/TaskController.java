package com.example.todo_app;

import java.util.Optional;

import jakarta.validation.Valid; // ★ インポートを追加

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // ★ インポートを追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private final TaskRepository repository;

    // コンストラクタ：Repositoryを自動で使えるようにする（DI）
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    // ========== R (Read) - タスクの一覧表示と新規タスク登録フォームを表示 ==========
    @GetMapping("/")
    public String list(Model model) {
        // DBから全タスクを取得し、画面（HTML）に渡す
        Iterable<Task> tasks = repository.findAll();
        model.addAttribute("tasks", tasks);
        
        // ★ ここから追加: Thymeleaf に「空のTaskオブジェクト」を渡す ★
        // これにより、list.html で #fields.hasErrors を安全に使えるようになる
        model.addAttribute("task", new Task());
        
        // "list.html" テンプレートを呼び出す
        return "list";
    }
    
    // ========== C (Create) - タスクの追加処理 ==========
    @PostMapping("/add")
    public String add(                      
                      // ★ ここから追加: Formデータとバリデーション結果を受け取る ★
                      @Valid Task newTask, // Taskオブジェクトとしてデータを検証
                      BindingResult bindingResult, // 検証結果を受け取る
                      Model model){
    	
    	// --- ★ 検証ロジックの追加 ★ ---
        if (bindingResult.hasErrors()) {
            // エラーがあった場合、タスク一覧を再取得して画面にエラーを表示
            Iterable<Task> tasks = repository.findAll();
            model.addAttribute("tasks", tasks);
            // エラーメッセージは BindingResult が自動で画面に渡します
            
            // 入力した値（newTask）を画面に保持したまま、list.htmlに戻る
            return "list"; 
        }
        // --- ★ 検証ロジックの追加ここまで ★ ---
        
     // 【重要】未完了の状態（isDone=false）を設定し、IDはDBに任せる
        newTask.setDone(false);
        
        repository.save(newTask);
        
        // トップページにリダイレクトして一覧画面に戻す
        return "redirect:/";
    }

    // ========== U (Update) - タスクの完了状態の切り替え ==========
    @PostMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        // IDでタスクを検索
        Optional<Task> taskOpt = repository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            // 完了状態を反転させる（完了なら未完了に、未完了なら完了に）
            task.setDone(!task.isDone());
            // DBに保存する（更新）
            repository.save(task);
        }
        return "redirect:/";
    }

    // ========== D (Delete) - タスクの削除処理 ==========
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        // IDを指定してDBからタスクを削除
        repository.deleteById(id);
        return "redirect:/";
    }
}

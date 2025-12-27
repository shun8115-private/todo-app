package com.example.todo_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class TaskController {

	private final TaskService taskService; // ★ TaskServiceに変更

	// コンストラクタもTaskServiceを受け取るように修正
    public TaskController(TaskService taskService) { // ★ TaskServiceに変更
        this.taskService = taskService;
    }

    // ========== R (Read) - タスクの一覧表示と新規タスク登録フォームを表示 ==========
    @GetMapping("/")
    public String list(//検索キーワードを受け取る（無ければ null が入る
            @RequestParam(required = false) String keyword,
            Model model) {
    	
    	// ★ ロジックをService層に移譲
        Iterable<Task> tasks = taskService.findTasks(keyword);
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        
        if (keyword != null && !keyword.isEmpty()) {
            // キーワードがある場合: 検索メソッドを実行する
            model.addAttribute("message", "キーワード「" + keyword + "」で検索しました。");
            
        } else {
            // キーワードがない場合: 全タスクを取得する
        	model.addAttribute("message", "完了したタスクは非表示です。");
        }
        
        // "list.html" テンプレートを呼び出す
        return "list";
    }
    
    // ========== C (Create) - タスクの追加処理 ==========
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute Task task, 
            		BindingResult result){
    	
    	if (result.hasErrors()) {
            return "list";
        }
        // ★ ロジックをService層に移譲
        taskService.add(task);
        return "redirect:/";
    }

    // ========== U (Update) - タスクの完了状態の切り替え ==========
    @PostMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        // IDでタスクを検索
    	// ★ ロジックをService層に移譲
        taskService.toggleDone(id);
        return "redirect:/";
    }

    // ========== D (Delete) - タスクの削除処理 ==========
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	// ★ ロジックをService層に移譲
        taskService.delete(id);
        return "redirect:/";
    }
}

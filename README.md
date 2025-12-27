# Simple TODO Application 📝
![Uploading スクリーンショット 2025-12-27 19.09.13.png…]()

Spring Boot で開発した、多機能なタスク管理アプリケーションです。
単なる CRUD 機能だけでなく、実務を意識した「サービス層の分離」や「高度な検索・ソート機能」を実装しています。

## 🚀 主な機能
- **タスク管理 (CRUD):** タスクの作成、一覧表示、完了、削除。
- **検索機能:** キーワードによるタスクの絞り込み検索。
- **自動ソート:** 優先度（高い順）および期限日（近い順<img width="703" height="443" alt="スクリーンショット 2025-12-27 19 09 13" src="https://github.com/user-attachments/assets/3edb8529-47a9-419b-97f2-8d691529b46b" />
）による自動並び替え。
- **フィルタリング:** 未完了のタスクのみを表示し、完了済みタスクを自動で非表示にする機能。


## 🛠️ 使用技術
- **Java 17**
- **Framework:** Spring Boot 3.x
- **Database:** H2 Database (In-memory)
- **Data Access:** Spring Data JDBC
- **Template Engine:** Thymeleaf
- **Validation:** Jakarta Validation

## 🏗️ 設計のこだわり
- **三層アーキテクチャ:** Controller, Service, Repository の役割を明確に分離し、保守性の高いコードを実現しました。
- **メソッド命名規則の活用:** Spring Data JDBC の命名ルールを活用し、複雑な SQL を書かずに高度なフィルタリングを実現しています。

## 🔧 実行方法
1. このリポジトリをクローンします。
2. Eclipse または IntelliJ でプロジェクトをインポートします。
3. `TodoAppApplication.java` を実行します。
4. ブラウザで `http://localhost:8080` にアクセスします。

-- H2 Databaseが自動で読み込み、taskテーブルを作成する
CREATE TABLE IF NOT EXISTS task (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    content     VARCHAR(255) NOT NULL,
    is_done     BOOLEAN DEFAULT FALSE,
    deadline    DATE,
    priority    INT DEFAULT 2
);
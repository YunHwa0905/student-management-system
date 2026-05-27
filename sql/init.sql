-- 학생 테이블
CREATE TABLE IF NOT EXISTS students (
    std_no   SERIAL PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    id       VARCHAR(30)  UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- 성적 테이블
CREATE TABLE IF NOT EXISTS scores (
    score_id SERIAL PRIMARY KEY,
    std_no   INT          NOT NULL,
    ko       INT          NOT NULL,
    en       INT          NOT NULL,
    ma       INT          NOT NULL,
    avg      NUMERIC(5,2) NOT NULL,
    grade    VARCHAR(2)   NOT NULL,
    FOREIGN KEY (std_no) REFERENCES students(std_no)
);
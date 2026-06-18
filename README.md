# 📊 학생 정보 및 성적 관리 시스템

학생 등록 및 과목별 점수를 관리하고, 평균과 등급을 자동으로 산출하는 웹 기반 성적 관리 시스템입니다.

---

## 🛠️ 기술 스택

![Java](https://img.shields.io/badge/Java_11-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring MVC](https://img.shields.io/badge/Spring_MVC_5.3.6-6DB33F?style=flat-square&logo=spring&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=flat-square&logo=mybatis&logoColor=white)
![Tomcat](https://img.shields.io/badge/Tomcat_9-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL_15-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=flat-square&logo=nginx&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

| 구분 | 기술 |
|------|------|
| Frontend | HTML / JavaScript (SPA) |
| Backend | Spring Legacy (MVC 5.3.6) / Java 11 / Tomcat 9 |
| Database | PostgreSQL 15 / MyBatis SqlSessionTemplate |
| Infra | Docker / Docker Compose / nginx |
| Build | Maven (WAR 패키징) |

---

## 📁 프로젝트 구조

```
student-management-system/
├── backend_logic/          # Spring Legacy 백엔드
│   ├── src/main/java/com/score/backend/
│   │   ├── controller/     # StudentController, ScoreController, AuthController
│   │   ├── service/        # StudentService, ScoreService
│   │   ├── repository/     # StudentRepository, ScoreRepository
│   │   └── entity/         # ScoreRequest, ScoreResponse, Student
│   ├── src/main/resources/
│   │   ├── mappers/        # StudentMapper.xml, ScoreMapper.xml
│   │   └── db.properties   # DB 연결 설정
│   ├── src/main/webapp/WEB-INF/spring/
│   │   └── servlet-context.xml  # Spring / MyBatis / DataSource 설정
│   └── Dockerfile
├── frontend/               # HTML/JS 프론트엔드
│   ├── index.html
│   ├── nginx.conf
│   └── Dockerfile
├── sql/
│   └── init.sql            # DB 초기화 DDL
└── docker-compose.yml
```

---

## 🏗️ 시스템 아키텍처

```
[Browser]
    ↓ HTTP 요청
[Frontend 컨테이너] - nginx:80
    ↓ /api/* 리버스 프록시
[Backend 컨테이너] - Tomcat 9:8080
    ↓ MyBatis SQL
[DB 컨테이너] - PostgreSQL:5432
    ↓ Volume 마운트 (데이터 영속화)
[db-data Volume]
```

---

## 🗄️ DB 설계

### students 테이블
| 컬럼 | 타입 | 제약조건 | 설명 |
|------|------|----------|------|
| std_no | SERIAL | PRIMARY KEY | 학번 (자동증가) |
| name | VARCHAR(50) | NOT NULL | 이름 |
| id | VARCHAR(30) | UNIQUE NOT NULL | 아이디 |
| password | VARCHAR(100) | NOT NULL | 비밀번호 |
| role | VARCHAR(10) | DEFAULT 'STUDENT' | 권한 (ADMIN/STUDENT) |

### scores 테이블
| 컬럼 | 타입 | 제약조건 | 설명 |
|------|------|----------|------|
| score_id | SERIAL | PRIMARY KEY | 성적 ID (자동증가) |
| std_no | INT | FK → students | 학번 |
| ko | INT | NOT NULL | 국어 점수 |
| en | INT | NOT NULL | 영어 점수 |
| ma | INT | NOT NULL | 수학 점수 |
| avg | NUMERIC(5,2) | NOT NULL | 평균 |
| grade | VARCHAR(2) | NOT NULL | 등급 (A~E) |

---

## 🔌 API 명세

### 인증
| Method | URL | 설명 |
|--------|-----|------|
| POST | /api/auth/login | 로그인 (id, password) |

### 학생
| Method | URL | 설명 |
|--------|-----|------|
| GET | /api/student | 전체 학생 조회 |
| POST | /api/student | 학생 등록 |
| DELETE | /api/student/{std_no} | 학생 삭제 |

### 성적
| Method | URL | 설명 |
|--------|-----|------|
| GET | /api/score | 전체 성적 조회 (순위 포함) |
| POST | /api/score | 성적 등록 |
| DELETE | /api/score/{score_id} | 성적 삭제 |
| GET | /api/score/my/{std_no} | 본인 성적 조회 |

---

## ⚙️ 핵심 로직

### 등급 계산
```java
double avg = Math.round((ko + en + ma) / 3.0 * 10) / 10.0;

if (avg >= 90)      grade = "A";
else if (avg >= 80) grade = "B";
else if (avg >= 70) grade = "C";
else if (avg >= 60) grade = "D";
else                grade = "E";
```

### 순위 계산 (동점자 동일 순위)
```sql
RANK() OVER (ORDER BY sc.avg DESC) AS rank
-- 예: 100, 90, 90, 80 → 1위, 2위, 2위, 4위
```

### 권한 분리
- `ADMIN`: 전체 학생/성적 CRUD 가능
- `STUDENT`: 본인 성적 조회만 가능
- 로그인 시 `role` 반환 → 프론트엔드에서 화면 분기

---

## 🚀 실행 방법

### 사전 요구사항
- Docker / Docker Compose 설치
- (백엔드 수정 시) Java 11, Maven 설치

### 1. 백엔드 빌드 (수정 시)
```bash
cd backend_logic
mvn clean package -DskipTests
```

### 2. Docker 이미지 빌드 & 실행
```bash
docker compose up -d --build
```

### 3. 접속
```
http://localhost
```

### 기본 관리자 계정
```
아이디: admin
비밀번호: admin1234
```

---

## 🐳 Docker Hub

```
yunhwa0905/score-backend   # 백엔드 이미지
yunhwa0905/score-frontend  # 프론트엔드 이미지
```

도커 허브 이미지로 실행:
```bash
docker compose up -d
```

---

## 🔧 트러블슈팅

### 1. nginx.conf 빌드 반영 안 됨
- **원인**: 파일명 오타 + Windows/WSL 경로 불일치
- **해결**: WSL에서 직접 파일 덮어쓰기 후 `--no-cache` 옵션으로 강제 재빌드

### 2. Mapped Statement 오류
- **원인**: Mapper XML 수정 후 Maven 빌드 누락으로 WAR에 미포함
- **해결**: Maven clean package 재실행 → Docker 이미지 재빌드

### 3. 로그인 후 화면 전환 안 됨
- **원인**: `classList` 방식이 정상 동작하지 않음
- **해결**: `style.display` 직접 조작 방식으로 변경

### 4. WSL Maven 빌드 실패
- **원인**: WSL Java 21과 Lombok 1.18.24 호환성 충돌
- **해결**: Eclipse (Java 11 환경)에서 빌드

---

## 📋 제출물 체크리스트

- [x] 소스코드 전체
- [x] Git 정리
- [x] 실행 화면 캡처
- [x] 설계 문서
- [x] 발표 PPT (배운 내용 + 트러블슈팅)

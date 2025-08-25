# 🍱 잔반플러팅 Backend

지역 반찬 매물을 기반으로 **사용자 맞춤형 반찬 추천과 경로 최적화**를 제공하는 백엔드 서버입니다.  
Spring Boot를 기반으로 구축되었으며, **FastAPI 기반 AI 추천 서버**와 연동되어 동작합니다.

---

## 🚀 Tech Stack
<img width="1045" height="844" alt="struct-be" src="https://github.com/user-attachments/assets/a10dd991-3e69-46dc-8eb0-152d27e3c1e4" />


### 🔹 Backend
- **Java 21**
- **Spring Boot 3.5.3**
- **MySQL** : 메인 데이터베이스
- **Swagger (springdoc-openapi)** : API 문서화

<img src="https://img.shields.io/badge/Java%2021-007396?style=flat-square&logo=java&logoColor=white" /> <img src="https://img.shields.io/badge/Spring%20Boot%203.5.3-6DB33F?style=flat-square&logo=springboot&logoColor=white" /> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" /> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black" />


### 🔹 AI / Recommendation
- **FastAPI (Python)** : AI 추천 서버
- **GPT-4o-mini**
    - 🍳 **요리 레시피 생성** (사용자가 구매한 반찬 기반)
    - 🥗 **식단 테마 기반 추천** (다이어트/키토/저염/벌크업/혈당)
- **Fine-tuning**
    - GPT-4o-mini를 **영양성분표 데이터로 파인튜닝**  
      → 매물의 실제 영양 성분 분석 후 맞춤형 추천 제공

<img src="https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white" /> <img src="https://img.shields.io/badge/GPT--4o%20mini-412991?style=flat-square&logo=openai&logoColor=white" />


### 🔹 DevOps
- **Gradle** : 빌드 & 의존성 관리
- **GitHub Actions** : CI/CD 파이프라인
    - Build & Test
    - Docker Build & Push → DockerHub 업로드
- **Docker** : 컨테이너화 및 배포

<img src="https://img.shields.io/badge/Gradle-023038?style=flat-square&logo=gradle&logoColor=white" /> <img src="https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white" /> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white" />  


---

## 📂 Project Structure
```text
📦 src
┣ 📂 main
┃ ┣ 📂 java/com/example/Centralthon
┃ ┃ ┣ 📂 domain           # 도메인 로직 
┃ ┃ ┣ 📂 global           # 공통 설정 및 유틸
┃ ┃ ┣ 📂 core/exception   # 전역 예외 처리
┃ ┣ 📂 resources
┃ ┃ ┣ application.properties   # 환경 설정
┣ 📂 test                 # 단위/통합 테스트
```

---

## 🛠 주요 기능

### 1. 📍 반찬 매물 조회
- 사용자의 현재 위치를 기준으로 **반경 2km 이내**의 매물 목록 조회
- 가게 정보, 메뉴 정보, 가격, 할인율 포함

### 2. 🚶 최적 경로 제공
- 사용자가 매물을 장바구니에 담고 픽업 예약하면  
  해당 가게들을 경유하는 **도보 최적 경로(TSP 기반)** 제공
- **Tmap API + 내부 최적화 알고리즘 활용**

### 3. 🍳 요리 레시피 추천 (AI)
- 구매한 반찬들을 조합하여 만들 수 있는 **레시피 자동 생성**
- **GPT-4o-mini 활용**

### 4. 🥗 맞춤형 식단 추천 (AI Fine-tuning)
- 다섯 가지 식단 테마 제공:
    - 다이어트 / 키토 / 저염 / 벌크업 / 혈당 조절
- **GPT-4o-mini Fine-tuning** → 실제 **영양성분표 데이터를 학습**시켜, 매물의 성분을 기반으로 테마별 맞춤형 추천 제공

---

## ⚙️ CI/CD Workflow

### 🔹 CI Pipeline
1. Gradle 빌드 → Spring Boot JAR 생성
2. Docker 이미지 빌드
3. DockerHub 푸시 (latest, commit sha 태그)

### 🔹 CD Pipeline
1. 대상 서버에서 DockerHub에서 pull
2. 컨테이너 재기동(교체 배포)


👉 GitHub Actions에서 **`main` 브랜치에 push 시 자동 실행**

---
## 📊 ERD
<img width="885" height="543" alt="image" src="https://github.com/user-attachments/assets/35bf67e7-59e4-4079-8ff5-2290b61343ed" />


---

## 📌 API 목록 (주요 엔드포인트)

- `GET /api/menus` → 사용자 위치 기반 반경 2km 내 매물 조회
- `POST /api/routes/directions` → 픽업 예약 기반 최적 경로 생성
- `POST /api/menus/tips` → 구매 반찬 기반 요리 레시피 추천 (AI)
- `POST /api/menus/recommend` → 식단 테마 기반 추천 (AI Fine-tuning)

Swagger 문서:  
👉 [https://52.78.244.98.nip.io/api/swagger-ui/index.html](https://52.78.244.98.nip.io/api/swagger-ui/index.html)

---



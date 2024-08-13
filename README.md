# 원티드 프리온보딩 백엔드 인턴십 선발과제 #
## 😁 소개
**이현영** (eter2hy@gmail.com)

## ✅ 서비스 개요
- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

## ✉️ 요구 사항
1. 채용 공고를 등록합니다.
2. 채용 공고를 수정합니다.
3. 채용 공고를 삭제합니다.
4. 채용 공고 목록을 가져옵니다.
5. 채용 공고 검색 기능을 구현합니다.
6. 채용 상세 페이지를 가져옵니다.
7. 채용 공고에 지원합니다.

## 🛠️ 기술 스택
<img src="https://img.shields.io/badge/Java 17-blue?style=for-the-badge&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/springboot 3.3.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/H2 Database-blue?style=for-the-badge&logo=H2&logoColor=white">

<img src="https://img.shields.io/badge/JPA-blue?style=for-the-badge&logo=JPA&logoColor=white"> <img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/IntelliJ IDEA-black?style=for-the-badge&logo=intellijidea&logoColor=white">

## 📌 ERD
![image](https://github.com/user-attachments/assets/c8dc512e-cdfc-4d11-9d65-55d0be2c02ce)


## 🧾 API 명세
base URL: `http://localhost:8000`
### 1. 채용 공고를 등록합니다.
#### Request
POST `/api/posts`
```
{
  "companyId": 1,
  "position":"백엔드 주니어 개발자",
  "compensation":1000000,
  "description":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skills":"Python"
}
```
#### Response
```
{
    "id": 4,
    "company": {
        "id": 1,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울"
    },
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "description": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
    "skills": "Python"
}
```

#### 1-1. 존재하지 않는 comapyId로 등록 시
```
company ID not found: 5
```

### 2. 채용 공고를 수정합니다.
#### Request
PUT `/api/posts/{id}`
```
{
    "position": "백엔드 시니어 개발자",
    "compensation": 3000000,
    "description": "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..",
    "skills": "Java"
}
```
#### Response
```
{
    "id": 1,
    "company": {
        "id": 1,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울"
    },
    "position": "백엔드 시니어 개발자",
    "compensation": 3000000,
    "description": "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..",
    "skills": "Java"
}
```
### 2-1. 채용 공고 수정 시 일부만 수정 가능
#### Request
```
{
    "position": "백엔드 주니어 개발자"
}
```
#### Response
```
{
    "id": 1,
    "company": {
        "id": 1,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울"
    },
    "position": "백엔드 주니어 개발자",
    "compensation": 3000000,
    "description": "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..",
    "skills": "Java"
}
```

### 3. 채용 공고를 삭제합니다.
#### Request
DELETE `/api/posts/{id}`

### 4. 채용 공고 목록을 가져옵니다.
#### Request
GET `/api/posts`

#### Response
```
[
    {
        "id": 1,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울",
        "position": "백엔드 주니어 개발자",
        "compensation": 3000000,
        "skills": "Java"
    },
    {
        "id": 2,
        "name": "네이버",
        "country": "한국",
        "region": "판교",
        "position": "Django 백엔드 개발자",
        "compensation": 1000000,
        "skills": "Django"
    },
    {
        "id": 3,
        "name": "카카오",
        "country": "한국",
        "region": "판교",
        "position": "프론트엔드 개발자",
        "compensation": 500000,
        "skills": "JavaScript"
    },
    {
        "id": 4,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울",
        "position": "백엔드 주니어 개발자",
        "compensation": 1000000,
        "skills": "Python"
    }
]
```

### 5. 채용 공고 검색 기능을 구현합니다.
#### Request
GET `/api/posts/search?search={keyword}`

#### Response
keyword가 판교일 때
```
[
    {
        "id": 2,
        "name": "네이버",
        "country": "한국",
        "region": "판교",
        "position": "Django 백엔드 개발자",
        "compensation": 1000000,
        "skills": "Django"
    },
    {
        "id": 3,
        "name": "카카오",
        "country": "한국",
        "region": "판교",
        "position": "프론트엔드 개발자",
        "compensation": 500000,
        "skills": "JavaScript"
    }
]
```

keyword가 원티드일 때
```
[
    {
        "id": 1,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울",
        "position": "백엔드 주니어 개발자",
        "compensation": 3000000,
        "skills": "Java"
    },
    {
        "id": 4,
        "name": "원티드랩",
        "country": "한국",
        "region": "서울",
        "position": "백엔드 주니어 개발자",
        "compensation": 1000000,
        "skills": "Python"
    }
]
```

### 6. 채용 상세 페이지를 가져옵니다.
#### Request
GET `/api/posts/{id}`

#### Response
```
{
    "id": 1,
    "name": "원티드랩",
    "country": "한국",
    "region": "서울",
    "position": "백엔드 주니어 개발자",
    "compensation": 3000000,
    "skills": "Java",
    "description": "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..",
    "others": [
        4
    ]
}
```

### 7. 채용 공고에 지원합니다.
#### Request
POST `/api/apply`
```
{
    "postId": 1,
    "userId": 1
}
```

#### Response
```
{
    "id": 1,
    "user": {
        "id": 1,
        "username": "user1"
    },
    "post": {
        "id": 1,
        "company": {
            "id": 1,
            "name": "원티드랩",
            "country": "한국",
            "region": "서울"
        },
        "position": "백엔드 주니어 개발자",
        "compensation": 3000000,
        "description": "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..",
        "skills": "Java"
    }
}
```

#### 7-1. 동일한 공고에 동일한 지원자가 지원 시 Response
```
An error occurred while applying: User has already applied
```

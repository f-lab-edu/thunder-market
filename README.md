# 썬더마켓

<p align="center" style="color:gray">
  <img style="margin:50px 0 10px 0" src="https://github.com/f-lab-edu/team-timing/assets/25719259/bf6659dd-6bb5-4a3c-aa3f-ec878906597e" alt="" width=300 />


## 프로젝트 가설
- 중고 아이폰 구매자는 구매 전 기기의 상세 정보를 최대한 많이 알고 싶어한다.
- 상품 상세 정보를 판매자와 연락 없이 얻고자 한다.
</br>
</br>

## 프로젝트 설명 (워킹 스켈레톤 / MVP / 아이스박스)
### 워킹 스켈레톤
- 판매 상품 등록 (이름, 가격)
- 판매 상품 목록 조회
- 판매 상품 조회
- 판매 상태 조회 및 변경

</br>

### MVP
- 판매 상품 검색 필터
  - 모델명, 가격, 색상, 저장용량, 구매 시기
- 판매 상품 등록
  - 동영상, 가격, 모델명, 색상, 저장용량, 상품 컨디션, 배터리 성능, 카메라 상태, 구성품, 구매 시기, 보증 기간, 직거래 장소, 택배비, 상품 사진, 메모
- 회원가입/로그인
- 거래내역 조회
- 판매 상태값(판매중/판매완료)
- 메시지 보내기/받기

</br>

### 아이스박스
- 찜
- 매물 알림
- 판매자 칭찬(별점 시스템)
- 바로 구매
- 구매/판매 목록 편집
- 소셜 로그인
- 신고
- 안전 거래
- 판매중인 것만 보기 필터

</br>
</br>
</br>

## 화면 (초안)
[https://ovenapp.io/](https://ovenapp.io/)
</br>
![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/4f86aafd-c1cc-400d-856f-9eae58f02d6b)
</br>
</br>
</br>

## 화면 (진행중)

![image](https://github.com/user-attachments/assets/380dc99c-6a64-42be-94bf-c89c65686336)
![image](https://github.com/user-attachments/assets/fcc248b2-4482-433d-ac75-589c8a899638)
</br>
</br>
</br>

## 프로젝트 아키텍처 (진행중)
[draw.io](https://draw.io "https://draw.io")
</br>
![image](https://github.com/f-lab-edu/team-timing/assets/25719259/ce963abe-0c6f-4e5c-ae41-ecf42909a599)
</br>
</br>
</br>

## ERD (진행중)
[https://dbdiagram.io/](https://dbdiagram.io/ "https://dbdiagram.io/")
</br>
![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/b347a282-8b08-4903-a990-6a79d610cd37)

</br>
</br>

## 브랜치 전략 / 선택 이유
### github flow 전략 도입
이 프로젝트는 기능 개발의 단위를 작게 가져가서 빠른 배포 후 빠른 피드백을 지향하므로, feature branch와 main 브랜치를 중심으로 심플하고 유연한 개발 흐름을 사용함

![image](https://github.com/user-attachments/assets/3abd4f85-a7b9-4155-b085-05186316e7c2)
</br>
</br>
</br>

## API Path 규칙
- /api/{버전} 으로 시작한다. (ex. /api/v1)
- RESTful API 설계를 사용한다.
  - 예시
  ```java
    // 사용자 정보 조회 API
    GET /api/v1/users/{userId}

    // 사용자 정보 생성 API
    POST /api/v1/users

    // 사용자 정보 수정 API
    PUT /api/v1/users/{userId}

    // 사용자 정보 삭제 API
    DELETE /api/v1/users/{userId}
    ```
    </br>
    </br>

## API Request, Response 흐름
- Controller는 Request DTO를 통해 요청값을 받아서 유효성을 검증한다.
- Request DTO를 Domain 객체로 변환하여 주요 서비스 로직을 수행한다.
- 모델 객체를 통해 DB 작업을 수행한다.
- 서비스 로직 수행 후 사용자에게 반환할 Response DTO 객체를 생성해서 Controller에 반환한다.
- Controller는 사용자에게 결과를 반환한다.

Product(상품) 도메인 request, response 흐름 예시
![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/93c30eb6-8ff1-4e41-8818-5700e2923ad7)

</br>
</br>

## 기술 환경
- Java
- JDK 21
- Gradle 8.5
- Spring Boot, Spring JPA, MySQL, Redis, Jenkins, Docker
</br>
</br>

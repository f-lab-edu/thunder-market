# 썬더마켓

<p align="center" style="color:gray">
  <img style="margin:50px 0 10px 0" src="https://github.com/f-lab-edu/team-timing/assets/25719259/bf6659dd-6bb5-4a3c-aa3f-ec878906597e" alt="" width=300 />


## 프로젝트 가설
- 중고 아이폰 구매자는
- 아이폰 거래에서 기본적으로 체크해야 할 제품 상태에 대해
- 판매자와의 연락 이전에
- 원하는 상태의 제품을 찾고 싶을 것이다.  
</br>
</br>

## 프로젝트 설명 (워킹 스켈레톤 / MVP / 아이스박스)
### 워킹 스켈레톤
- 판매 상품 등록 (이름, 가격)
- 판매 상품 목록 조회
- 판매 상품 조회
- 판매 상태 조회 및 변경
### MVP
- **판매 상품 검색**
  - 상품 가격, 제품명, 색상, 상품 컨디션, 배터리 성능, 카메라 상태, 구성품, 구매 시기, 보증 기간, 직거래 장소, 택배비
- 판매 상품 등록
  - 상품 가격, 제품명, 색상, 상품 컨디션, 배터리 성능, 카메라 상태, 구성품, 구매 시기, 보증 기간, 직거래 장소, 택배비, 상품 사진, 메모
- 로그인 (소셜로그인)
- 거래내역 조회

### 아이스박스
- 판매자 칭찬
- 구매 요청 (채팅)
- 바로 구매
- 안전 거래
- 네고 요청
- 찜
- 신고
</br>
</br>
</br>

## 화면
- [https://ovenapp.io/](https://ovenapp.io/)
  </br>
  ![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/4f86aafd-c1cc-400d-856f-9eae58f02d6b)
  </br>
  </br>
  </br>

## 프로젝트 아키텍처 (수정중)
- [draw.io](https://draw.io "https://draw.io")
  </br>
  ![image](https://github.com/f-lab-edu/team-timing/assets/25719259/ce963abe-0c6f-4e5c-ae41-ecf42909a599)
  </br>
  </br>
  </br>

## ERD (수정중)
- [https://dbdiagram.io/](https://dbdiagram.io/ "https://dbdiagram.io/")
  </br>
  ![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/b347a282-8b08-4903-a990-6a79d610cd37)

## 브랜치 전략 / 선택 이유
- github flow
  - 이 프로젝트에서는 기능 단위를 작게 가져가서 빠른 배포 후 빠른 피드백을 지향한다.
  - 따라서 release, hotfix, pre-production 브랜치 등의 과정이 없는 가장 간단한 브랜치 전략을 선택했다.
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
- (Repository 접근 부분은 추후 수정 예정)
- 서비스 로직 수행 후 사용자에게 반환할 Response DTO 객체를 생성해서 Controller에 반환한다.
- Controller는 사용자(View)에게 결과를 반환한다.
- 다음은 Product(상품) 도메인 request, response 흐름 예시이다.
- ![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/93c30eb6-8ff1-4e41-8818-5700e2923ad7)

    </br>
    </br>

## 기술 환경
- Java
- JDK 21
- Gradle 8.5
- Spring Boot, Spring JPA, MySQL, Redis, Jenkins, Docker
  </br>
  </br>

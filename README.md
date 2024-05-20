# thunder-market

<p align="center" style="color:gray">
  <img style="margin:50px 0 10px 0" src="https://github.com/f-lab-edu/team-timing/assets/25719259/bf6659dd-6bb5-4a3c-aa3f-ec878906597e" alt="" width=300 />

## 프로젝트 이름
- 썬더마켓
</br>
</br>
</br>

## 프로젝트 소개
- 아이폰을 중고거래 할 때 게시글 작성에 막막했던 적이 있으신가요?
- 제공된 몇 가지 항목을 선택하고 입력하면, 자동으로 자연스러운 게시글이 생성됩니다.
- 이 기능을 통해 반복적인 작업을 줄여 중고거래의 접근성을 높이고, 판매자와 구매자 간의 소통 비용을 줄여 쉽고 편한 중고거래를 가능하게 합니다.
</br>
</br>

## 프로젝트 가설
- 아이폰 중고거래 판매자는 게시글을 더 쉽게 작성하고 싶을 것이다.
</br>
</br>

## 기술 환경
- Java
- JDK 21
- Gradle 8.5
- Spring Boot, Spring JPA, MySQL, Redis, Jenkins, Docker
  </br>
  </br>

## 프로젝트 설명(워킹 스켈레톤 / MVP / 아이스박스)
### 워킹 스켈레톤
- 판매 상품 등록 (이름, 가격)
- 판매 상품 목록 조회
- 판매 상품 조회
- 판매 상태 조회 및 변경
### MVP
- **판매 상품 등록 (스마트 템플릿)**
  - 사진, 상품 이름, 저장 용량, 색상, 상품 컨디션(외관, 기능), 배터리 성능, 카메라 상태, 구성품 여부, 구매 시기, 보증 여부, 직거래 장소, 택배비 여부
  - 위 내용들을 체크하면 사람이 작성한 것처럼 게시글 내용을 작성해준다.
- 로그인(소셜로그인)
- 구매 이력 조회
- 판매 이력 조회

### 아이스박스
- 판매자 칭찬
- 구매 요청(채팅)
- 바로 구매
- 안전 거래
- 네고 요청
- 찜
- 신고
</br>
</br>
</br>

## 브랜치 전략 / 선택 이유
- github flow
  - 이 프로젝트에서는 기능 단위를 작게 가져가서 빠른 배포 후 빠른 피드백을 지향한다.
  - 따라서 release, hotfix, pre-production 브랜치 등의 과정이 없는 가장 간단한 브랜치 전략을 선택했다.
</br>
</br>
</br>

## 프로젝트 아키텍처
[draw.io](https://draw.io "https://draw.io")
![image](https://github.com/f-lab-edu/team-timing/assets/25719259/ce963abe-0c6f-4e5c-ae41-ecf42909a599)
</br>
</br>
</br>

## 화면
[https://ovenapp.io/](https://ovenapp.io/)
![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/4e159260-1dd3-40d8-a2d0-2d9077f6e4da)
</br>
</br>
</br>

## ERD
[https://dbdiagram.io/](https://dbdiagram.io/ "https://dbdiagram.io/")
![image](https://github.com/f-lab-edu/thunder-market/assets/25719259/b347a282-8b08-4903-a990-6a79d610cd37)
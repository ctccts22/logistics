
## 물류관리프로젝트(개인프로젝트)

이 프로젝트는 B2B 통합 물류 관리 시스템을 개발하는 것입니다.

### 데모 링크
[여기를 클릭하십시오](http://131.186.18.42/)

### 제작 기간 & 참여 인원
이 프로젝트는 개인 프로젝트로 진행되며, 제작 기간은 2주입니다.

### 사용한 기술
* Spring Boot 3.0.4
* Spring Security 6.0.2
* Thymeleaf 3.1.1
* Mysql 8.0.32
* JPA 6.0.6
* QueryDSL 5.0.0
* Oracle Cloud
* Cent OS 8
* Jenkins
* vue.js -> main 페이지만(간단한 컴포넌트를 이용한 그래프 만들기 -> 아직 기초단계 학습중입니다)

## 데이터베이스 구조
프로젝트에서 사용하는 데이터베이스 테이블은 다음과 같습니다.

<img width="792" alt="스크린샷 2023-06-03 오후 4 19 07" src="https://github.com/ctccts22/logistics/assets/115934236/a92f87d1-1422-498f-ab1d-8170ede90449">

## 시스템 구성
<p>이 시스템은 다음과 같은 주요 기능들을 제공합니다:</p>

<ol>
<li><strong>로그인 시스템</strong>: 회원은 총 3개의 역할이 있습니다. 'Admin'은 모든 권한을 가지고 홈페이지의 모든 페이지를 관리할 수 있습니다. 'Manager'는 관리자 페이지를 제외한 모든 페이지에 권한이 있습니다. 'Employee'는 관리자 페이지 및 사업자 관리를 제외한 모든 페이지에 권한이 있습니다.</li>
<li><strong>회원 가입/수정/탈퇴</strong>: 회원 가입은 기본적인 정규 표현식과 유효성 검사 기능이 있습니다.</li>
<li><strong>사업자 관리</strong>: 사업자는 총 3가지 타입이 존재합니다. Logistic_provider는 DHL, FedEx와 같은 물류 회사입니다. 이 회사는 물건을 창고에 관리하거나 산적하는 역할을 합니다. Customer는 바이어입니다. Supplier는 물건을 공급하는 회사입니다. 운영자와 매니저는 회사와 계약된 3가지 타입의 회사를 플랫폼에 등록, 수정, 삭제할 수 있습니다.</li>
<li><strong>물류 창고 관리</strong>: 물류 창고(warehouse)는 logistic_provider와 연관이 있습니다. 물류 창고를 소유하고 있는 물류 회사와 물류 창고 위치를 보여줍니다.</li>
<li><strong>재고 관리</strong>: 재고 관리는 inventory_item 테이블과 inventory_record 테이블로 관리됩니다. Supplier가 공급할 물건을 등록하고 재고 수량 및 재고를 담당하는 물류 관리 회사를 보여줍니다.</li>
<li><strong>주문 관리</strong>: 주문 관리는 order 테이블과 order_item로 관리됩니다. Customer가 회사에 발주를 넣으면 플랫폼을 통해 상세 주문 기록을 관리합니다.</li>
<li><strong>운송 차량 관리</strong>: 운송 차량 관리는 배송에 사용할 운송 수단 및 사용 가능한 상태를 나타냅니다. 운송 상태는 상시 업데이트 가능합니다.</li>
<li><strong>산적 관리</strong>: 물건 산적 상태를 관리합니다. 산적 상태가 Arrived로 업데이트 완료되면, 배송 관리에서 등록할 수 있습니다.</li>
<li><strong>배송 관리</strong>: 최종 배송 상태를 나타냅니다.</li>
</ol>

## 진행 현황
<p>현재 더 나은 포트폴리오를 위해 지속적인 업데이트를 진행중입니다:</p>
<p>사업자 관리 전면 수정</p>
<p>1. 카카오 맵 API 와 주소 API를 활용해 사업자주소와 위치를 바로 파악 할 수 있습니다.</p>
<img width="792" src="https://github.com/ctccts22/logistics/assets/115934236/255aee97-50d4-4e4f-be32-39db6617aba0">
<p>2. 회원목록UI 수정 기존 테이블UI 가독성 문제해결. 다른 테이블도 세부적인 수정 예정</p>
<img width="792" src="https://github.com/ctccts22/logistics/assets/115934236/c1acb4be-8c1a-4f4e-832b-979ae00d80c0">
<p>3. 게시판 추가(메세지 국제화적용) -> 기본적인 CRUD, 조회수 증가 그리고 페이징처리</p>
<img width="792" src="https://github.com/ctccts22/logistics/assets/115934236/893121cb-f43c-4644-b728-a0f3b2bb6368">
<p> -> Create: 스프링시큐리티(Authentication)사용으로 로그인한 유저가 글을 등록하면 자동으로 유저닉네임이 게시판 글쓴이로 데이터 베이스에 저장됩니다.</p>
<img width="792" src="https://github.com/ctccts22/logistics/assets/115934236/b0fe1f71-7126-4db9-b364-af9842509d40">
<p> -> Update & Delete: 스프링시큐리티사용으로 어드민은 모든 글을 수정 삭제 할수 있지만, 일반 유저는 자신의 글만 수정삭제가 가능합니다.</p>
<img width="792" src="https://github.com/ctccts22/logistics/assets/115934236/f85e61e9-fd11-476f-8a31-797cce5d2321">




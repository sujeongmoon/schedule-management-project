## 스케줄 관리 프로그램

* SpringBoot를 사용해서 만든, **스케줄을 관리하는 프로그램**입니다.


* 1.일정 작성, 
* 2.선택한 일정 조회, 
* 3.일정 목록 조회, 
* 4.선택한 일정 수정, 
* 5.선택한 일정 삭제
* 기능을 제공합니다.

---
### 사용 방법

* 해당 프로젝트에서 ScheduleManagementProjectApplication을 실행시킨 뒤, POSTMAN을 통해 프로그램의 동작을 확인할 수 있습니다.
* ScheduleController.java 내의 주석을 참고하여 프로그램을 실행시켜보세요.
---
### 유스케이스

![유스케이스](https://github.com/sujeongmoon/schedule-management-project/assets/163665929/017e4744-e379-4fef-8444-f7e9a046754c)

---

### API명세서
![API명세서](https://github.com/sujeongmoon/schedule-management-project/assets/163665929/152b1c2f-2eb0-4c5e-b7e9-cdc19010f38b)

* ID 필드값 이름이 달라졌다.
* 선택한 일정 수정 기능의 request 정보가 들어가지 않았다.

---
### ERD 다이어그램
![ERD](https://github.com/sujeongmoon/schedule-management-project/assets/163665929/96b2c095-fe99-4b44-b7c5-913f91e1f815)

* 처음에 구상했던 테이블은 Manager 테이블이 따로 있었다.
* date(createdAt)의 type을 결정하지 못했었다.
* Schedule ID의 타입이 int였지만 Long으로 바뀌었다.

---

### 프로젝트를 진행하며

* 처음에 SpringBoot 강의를 완강하지 못하고 프로젝트 구상을 시작했다가 결국 마지막 날에 급하게 강의를 완강하면서 동시에 프로젝트를 마무리하게 되었다.


* 따라서 강의 실습 내용을 많이 참고하여 완성한 코드가 되었다. 초기세팅 커밋과 기본 뼈대 완성 커밋 사이의 간극이 커서, 순차적으로 기능을 만들고 커밋을 했다면 더 좋았을 것 같아 아쉽다.


* createdAt이 SQL에 저장되었을 때 date 타입을 갖게하고 싶어 @Temporal 어노테이션을 사용하였다. @Temporal을 사용하기 위해 자바 필드타입을 date로 설정해주었더니 ScheduleController의 createSchedule 실행 시 "createdAt": "2008-05-25T00:00:00.000+00:00"로 출력되는 이슈가 있어 아쉬웠다.


* Spring을 써보는 건 처음이라 조금 헤맸다. 복습과 더 많은 공부가 필요해보인다!
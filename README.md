## 스케줄 관리 프로그램 Ver.2

* SpringBoot를 사용해서 만든, **스케줄을 관리하는 프로그램**입니다.


------------
* 일정 작성, 
* 선택한 일정 조회, 
* 일정 목록 조회, 
* 선택한 일정 수정, 
* 선택한 일정 삭제
* (이하 추가)
* 1. 댓글 작성
* 2. 댓글 수정
* 3. 댓글 삭제
* 4. 회원가입
* 5. 로그인
* 기능을 제공합니다.

---
### 사용 방법

* 해당 프로젝트에서 ScheduleManagementProjectApplication을 실행시킨 뒤, POSTMAN을 통해 프로그램의 동작을 확인할 수 있습니다.
* Controller 패키지 내의 주석을 참고하여 프로그램을 실행시켜보세요.
* 댓글 작성, 댓글 수정, 댓글 삭제를 시도하는 경우, 로그인 이후 생성된 토큰을 헤더에 넣어야지만 실행 가능합니다.
---


### API명세서
![image](https://github.com/sujeongmoon/schedule-management-project/assets/163665929/3cc9853c-6d96-4797-91b5-ee8f2e3be4f9)

---
### ERD 다이어그램
![image](https://github.com/sujeongmoon/schedule-management-project/assets/163665929/9844bf64-0e4c-4ec2-a29f-edea6e5b1d1e)


---

### 프로젝트를 진행하며

* Filter 사용 없이 프로젝트를 구현하였다. Spring Security를 사용해도 된다는 걸 나중에서야 알게 되어 아쉬웠다.

* 댓글 입력 시 Schedule을 입력받을 때 Long으로 받는데(Schedule_id), 해당 id를 언제 Schedule 객체로 바꾸어야하는지 잘 모르겠다. 현재 구조는 Long타입 id를 계속 인자로 전달받는 식이다. RequestDto에서 변환하면 좋을 것 같은데 Repository가 Service단에 있다 보니까 Dto에서 Repository를 또 import 시키긴 애매하다고 판단해서 그렇게 해보았는데 좋은 구조인지는 모르겠다.

* 토큰 생성을 제대로 이해하지 못한 것 같다. 현재 구현한 코드에서는 어떻게 생성되고 전달되는지 알 것 같은데, Client 헤더에 쿠키를 포함시킨다는 현재 방법 말고 JWT를 직접 HTTP에 담아서 전달시킨다는 방식은 또 무슨 소리인지 모르겠다. 토큰 관련 공부를 더 해야겠다.

---

이전 내용
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
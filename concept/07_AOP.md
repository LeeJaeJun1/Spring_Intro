### AOP가 필요한 상황

모든 메소드의 호출 시간 파악
공통 관심 사항 vs 핵심 관심 사항

#### 시간 측정 로직 
memberController -> memberService -> memberRepository

### AOP

공통 관심 사항과 핵심 관심 사항을 분리한다.
시간 측정 로직 : 공통 관심 사항
회원 가입, 인원 조회 : 핵심 관심 사항

원하는 곳에 공통 관심 사항을 적용한다.
@Around("execution(* hello.hello_spring..*(..))") 위치 지정

AOP를 적용하면 프록시에서 한번 거친 뒤, joinPoint.proceed()를 통해
실제 Controller, service, repository에 접근한다.




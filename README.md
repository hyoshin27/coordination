# 개발 과제 - coordination

## environment
java17, spring boot3.3.3, database h2

## DB
- table 정보 resources/schema.sql, 초기 데이터 resources/data.sql를 만들어 서버를 구동하면 자동적으로 데이터가 셋팅 되도록 하였습니다.
- 메모리 데이터로 시작할 때마다 초기화 되어 데이터가 세팅 되도록 만들게 하였습니다.
- 디비 확인 http://localhost:8080/h2-console/login.do
- 로그인 정보 sa/1234

## API Test 페이지 
- swagger http://localhost:8080/swagger-ui/index.html

## 과제를 마치며
### 작업순서 & 느낀점
- 데이터 설계를 할 때 브랜드, 상품, 카테고리 이렇게 3가지를 생각했는데 카테고리 데이터는 변화가 적고 변경하는 API가 없어서 enum으로 만들었습니다.
- 서버를 구동 하면 과제에서 주어진 초기 데이터를 분석한 스키마 설계에 맞게 데이터를 입력시켰습니다.
- 그 후에 최저가 API에서 샘플 데이터를 내려주는 api를 만들었습니다.
- 상품, 브랜드 CRUD API에서 초기 데이터가 아닌 추가 데이터를 입력/삭제/변경 할수 있는 API를 만들었습니다.
- 추가로 데이터를 입력/삭제/변경하며 최저가 API를 실제 데이터에 맞게 구현하고 exception을 생성하였습니다.
- 결과가 예상대로 데이터가 나오는지 테스트 코드를 입력하였습니다.
- 테스트 코드는 외부 종속성이 없고 h2 database만 사용하고 있어 이 repository 부분 만을 Test double 객체로 mocking하여 작성하였습니다.
- 과제를 봐주셔서 감사합니다.



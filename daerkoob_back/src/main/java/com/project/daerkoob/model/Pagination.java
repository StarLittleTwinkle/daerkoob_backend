package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
-- Pagination model
해당 page 가 어떠한 reviewId를 검색할 것인지 내포하고 있어야함.
그럴려면 일단 , pagination의 최소 정보인 한 페이지당 개수 , 그리고 페이지 번호, 그 다음에 total이 필요하다.
일단 지금은 사용자가 다른 url을 방문했다가 오더라도 그대로 유지되는 것을 구현하는 것은 아니니 getQueryString은 구현하지 않도록 한다.
일단 해당 book에 대한 review를 불러오는 것부터 한번해보자.
pagination 에서 가장 중요한 , Pageable 객체로 넘길 때 PageRequest.of를 사용하는데 거기서 사용하는 변수들이 다 int형이라서
꼭 Integer를 사용해주어야 한다 , 근데 그러지 않아도 되겠지만 , 나로서는 지금 Long to int 가 잘 안되고 있어서 이렇게 하는 게 편한 듯하다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private Integer pageNumber = 1; // 일단 확실하게 볼 수 있도록 pageSize 와 pageNumber는 조건 이렇게 하도록 하자
    private Integer pageSize = 15;
    private Object id; // 일단 해당 객체로 다 탐색하는 형식으로 가고 있다 그래서 Object로 받아서 pagination 객체에 저장하는 형태로 가야할 듯
    private Integer totalRecordCount; // 총 받게 되는 record 개수
}

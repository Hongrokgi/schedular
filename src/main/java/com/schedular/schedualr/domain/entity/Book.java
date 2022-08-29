package com.schedular.schedualr.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
*  인덱스(index) 데이터베이스의 테이블에 대한 검색 속도를 향상시켜주는 자료구조.
*  테이블의 특정 컬럼에 인덱스를 생성하면, 해당 컬럼의 데이터를 정렬한 후 별도의 메모리 공간에 데이터의 물리적 주소와 함께 저장. 컬럼의 값과 물리적 주소를 (key, value)의 한 쌍으로 저장
*  인덱스는 책에서의 목차 혹은 색인이라고 생각. 책에서 원하는 내용을 찾을 때, 목차나 색인을 이용하면 훨씬 빠르게 찾을 수 있는데, 마찬가지로 테이블에서 원하는 데이터를 찾기 위해 인덱스를 이용하면
*  빠르게 찾을 수 있다. 그러므로 데이터 = 책의 내용, 인덱스 = 책의 목차, 물리적 주소 = 책의 페이지 번호
*  <장점>
*  테이블을 검색하는 속도와 성능 향상. 핵심은 인덱스에 의해 데이터들이 정렬된 형태를 갖는다는 것. 기존에는 Where 문으로 특정 조건의 데이터를 찾기 위해서 테이블의 전체를 조건과 비교해야 하는 풀 테이블 스캔(Full Table Scan)
*  작업이 필요했는데, 인덱스를 이용하면 데이터들이 정렬되어 있기 때문에 조건에 맞는 데이터를 빠르게 찾을 수 있다. 또 ORDER BY 문이나 MIN/MAX 같은 경우도 이미 정렬이 되어 있기 때문에 빠르게 수행할 수 있다.
*  <단점>
*  인덱스가 항상 정렬된 상태로 유지되어 오는 장점도 있지만, 그에 따른 여러 단점도 존재
*  1. 인덱스를 관리하기 위한 추가작업이 필요
*  2. 추가 저장 공간 필요
*  3. 잘못 사용하는 경우 오히려 검색 성능 저하
*  인덱스를 항상 정렬된 상태로 유지해야 하기 때문에 인덱스가 적용된 컬럼에 삽입(INSERT), 삭제(DELETE), 수정(UPDATE) 작업을 수행하면 다음과 같은 추가 작업이 필요
*  -INSERT : 새로운 데이터에 대한 인덱스를 추가
*  -DELETE : 삭제하는 데이터의 인덱스를 사용하지 않는다는 작업 수행
*  -UPDATE : 기존의 인덱스를 사용하지 않음 처리, 갱신된 데이터에 대한 인덱스 추가
*  이처럼 인덱스의 수정도 추가적으로 필요하기 때문에 데이터의 수정이 잦은 경우 성능 ↓, 또한 데이터의 인덱스를 제거하는 것이 아니라 사용하지 않음으로 처리하고 남겨두기 때문에
*  수정 작업이 많은 경우 실제 데이터에 비해 인덱스가 과도하게 커지는 문제점이 발생할 수 있다. 별도의 메모리 공간에 저장되기 때문에 추가 저장공간이 많이 필요
*  또한 인덱스는 전체 데이터의 10~15% 이상의 데이터를 처리하거나, 데이터의 형식에 따라 오히려 성능이 낮아질 수 있다. 예를 들어 나이와 성별과 같이 값의 range 가 적은 컬럼인 경우 인덱스를 읽고 나서 다시 많은 데이터를 조회해야 하기 때문에
*  비효율적이다 -> 이 부분은 이해 안됨.
*
*  3. 인덱스를 사용하면 좋은 경우
*  인덱스를 효율적으로 사용하기 위해선 데이터의 range 가 넓고 중복이 적을수록, 조회가 많거나 정렬된 상태가 유용한 컬럼에 사용하는 것이 좋다
*  ● 규모가 큰 테이블
*  ● INSERT, UPDATE, DELETE 작업이 자주 발생하지 않는 컬럼
*  ● WHERE 나 ORDER BY, JOIN 등이 자주 사용되는 컬럼
*  ● 데이터의 중복도가 낮은 컬럼
*
*  4.인덱스의 자료구조
*  인덱스는 여러 자료구조를 이용해서 구현할 수 있는데, 대표적으로 HashTable, B+Tree
*  1. 해시테이블(Hash Table)
*  Key 와 value 를 한쌍으로 데이터를 저장하는 자료구조. (key,value)로 쌍을 표현하며, key 값을 이용해 대응되는 value 값을 구하는 방식. 해시 충돌이라는 변수가 존재하지만 평균적으로 0(1)의 매우 빠른 시간만에 원하는 데이터를 탐색할 수 있는 구조
*  해시 테이블을 이용한다면 인덱스는 (key,value) = (컬럼의 값, 데이터의 위치)로 구현하는데, 해시테이블은 실제로 인덱스에 잘 사용X
*  해시테이블은 등호(=) 연산에 최적화되어있기 때문. 데이터베이스에선(<,>)연산이 자주 사용되는데, 해시 테이블 내의 데이터들은 정렬되어 있지 않으므로 특정 기준보다 크거나 작은 값을 빠른 시간내에 찾을 수가 없다.
*
*  2.B+Tree
*  기존의 B-Tree는 어느 한 데이터의 검색은 효율적이지만, 모든 데이터를 한 번 순회하는 데에는 트리의 모든 노드를 방문해야 하므로 비효율적. 이러한 점을 개섢시킨게 B+Tree
*
*
*
* */
@Entity
//@Table(indexes = @Index(columnList = "publishedDateOn")) // 지정한 컬럼에 Index 생성. Key 이름은 무작위로 생성되는데 이를 @Index 속성 중 name 을 사용하여 이름 지정 가능
//@Table(indexes = @Index(name = "idx_book_name_published_date", columnList = "publishedDateOn, name")) 복합 인덱스 생성(Multi column index) columnList 에 comma 를 사용하여 컬럼을 나열
@Table(indexes = @Index(name = "unique_idx_book_name_author", columnList = "name, author", unique = true))
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private LocalDateTime publishedDateOn;

    public Book(String name, String author, LocalDateTime publishedDateOn) {
        this.name = name;
        this.author = author;
        this.publishedDateOn = publishedDateOn;
    }
}

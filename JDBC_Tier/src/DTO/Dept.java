package DTO;

import lombok.Data;

/*
 DTO or Vo or Domatin
 데이터베이스에 있는 Dept  테이블과 1:1 매핑 클래스
 Dept 데이터 1건을 담을 수 있는 클래스

 new Dept() >> select * from dept where dpetno = 10;
 
 
 */
//lombok을 사용하면 자동으로 getter setter toString 생성

@Data
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
}

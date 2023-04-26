package DTO;

import java.util.List;
import java.util.Scanner;

import DAO.DeptDao;

public class Program {
	public static void main(String[] args) {
		DeptDao ddao = new DeptDao();	
		
		Scanner sc = new Scanner(System.in);
				int num = 0;
		do {
			
			System.out.println("1. 전체조회 2. 조건조회 3. 삽입 4. 삭제 5. 수정 ");
			num = sc.nextInt();	
			switch(num) {
			case 1:
				ddao.getDeptAllList();
				List<Dept> depts = ddao.getDeptAllList();
				for (Dept dept : depts) {
					System.out.println(dept.toString());
				};
				break;
			case 2: 		
				ddao.getDept();	
				Dept dt = new Dept();
				dt = ddao.getDept();	
				System.out.println(dt.toString());
				break;
			case 3: ddao.insertDept();
				break;
			case 4: ddao.deleteDept();
				break;
			case 5: ddao.updateDept();
				break;
			}
			
		} while(num !=9);

	}
}


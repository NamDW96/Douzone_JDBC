package DTO;
import java.util.Scanner;

import DAO.EmpDAO;

public class Program {
	public static void main(String[] args) {
		
		EmpDAO empdao = new EmpDAO();
		
		
		
		Scanner sc = new Scanner(System.in);
		empdao.searchEmpByName();
		
//		int num = 0;
//		do {
//			
//			System.out.println("1. 전체조회 2. 조건조회 3. 삽입 4. 삭제 5. 수정 6. 이름검색");
//			num = sc.nextInt();	
//			switch(num) {
//			case 1: empdao.getEmpAllList();
//				break;
//			case 2: empdao.getEmp();
//				break;
//			case 3: empdao.insertDataEmp();
//				break;
//			case 4: empdao.deleteEmp();
//				break;
//			case 5: empdao.updateEmp();
//				break;
//			case 6: empdao.searchEmpByName();
//				break;	
//			}
//			
//		} while(num !=9);
		
	}
	
}

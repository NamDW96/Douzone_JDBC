package Test;

import java.util.Scanner;

import DAO.EmpDAO;

public class Test2 {
	public static void main(String[] args) {
		EmpDAO edao = new EmpDAO();

		Scanner sc = new Scanner(System.in);
		String num = "";
		do {

			System.out.println("");
			System.out.println("===============사원관리메뉴입니다============ ");
			System.out.println("1. 전체조회 2. 조건조회 3. 삽입 4. 삭제 5. 수정  ");
			System.out.println("======================================== ");
			System.out.printf("사용자 입력 >> ");
			num = sc.nextLine();
			
			switch (num) {
			case "1":
				edao.getAllEmpList();
				break;
			case "2":
				edao.getEmp();
				break;
			case "3":
				edao.insertDataEmp();
				break;
			case "4":
				edao.deleteEmp();
				break;
			case "5":
				edao.updateEmp();
				break;
			}

		} while (num != "9");

	}	
}

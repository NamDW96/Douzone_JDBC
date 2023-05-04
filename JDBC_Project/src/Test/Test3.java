package Test;

import java.util.Scanner;

import DAO.WorktDAO;

public class Test3 {
	public static void main(String[] args) {
		WorktDAO wdao = new WorktDAO();

		Scanner sc = new Scanner(System.in);
		int num = 0;
		do {
			System.out.println("");
			System.out.println("===============근퇴관리메뉴입니다============ ");
			System.out.println("1. 전체조회        2. 출근         3. 퇴근 ");
			System.out.println("======================================== ");
			System.out.printf("사용자 입력 >> ");
			
			num = sc.nextInt();
			
			switch (num) {
			case 1:
				wdao.getAttendList();
				break;
			case 2:
				wdao.insertStime();
				break;
			case 3:
				wdao.insertEtime();
				break;
			}

		} while (num != 9);

	}	
}

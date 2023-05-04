package Test;

import java.util.Scanner;

import DAO.HoltDAO;

public class Test4 {
	public static void main(String[] args) {
		HoltDAO hdao = new HoltDAO();

		Scanner sc = new Scanner(System.in);
		int num = 0;
		do {
			System.out.println("");
			System.out.println("===============휴가관리메뉴입니다============ ");
			System.out.println("1. 휴가조회      2. 휴가신청      3. 휴가취소 ");
			System.out.println("======================================== ");
			System.out.printf("사용자 입력 >> ");
			
			num = sc.nextInt();
			
			switch (num) {
			case 1:
				hdao.getAllHOlList();
				break;
			case 2:
				hdao.insertDataHOl();
				break;
			case 3:
				hdao.deleteHolCheck();
				break;
			}

		} while (num != 9);

	}	
}

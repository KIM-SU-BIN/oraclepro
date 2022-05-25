package com.javaex.phone;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PhoneDao phoneDao = new PhoneDao();

		// 출력
		System.out.println("********************************************************");
		System.out.println("*                전화번호 관리 프로그램                *");
		System.out.println("********************************************************");

		// 반복
		while (true) {
			List<PersonVo> pList = phoneDao.phoneSelect();

			System.out.println("");
			System.out.println(" 1.리스트 | 2.등록 | 3.수정 | 4.삭제 | 5.검색 | 6.종료" );
			System.out.println("----------------------------------");
			System.out.print("> 메뉴번호 : ");
			int n = sc.nextInt();

			// 종료
			if (n == 6) {
				System.out.println("***********************");
				System.out.println("*     종료되었습니다.     *");
				System.out.println("***********************");
				break;

				// 리스트
			} else if (n == 1) {
				System.out.println("< 1. 리스트 >");
				System.out.println("");

				// 출력하기
				for (int i = 0; i < pList.size(); i++) {
					int personId = pList.get(i).getPersonId();
					String name = pList.get(i).getName();
					String hp = pList.get(i).getHp();
					String company = pList.get(i).getCompany();
					System.out.println(personId + ".\t" + name + "\t" + hp + "\t" + company);

				}

				
				// 등록하기
			} else if (n == 2) {
				System.out.println("<2. 등록>");
				System.out.println(">이름 : ");
				String name = sc.next();

				System.out.println(">휴대전화 : ");
				String hp = sc.next();

				System.out.println(">회사전화 : ");
				String company = sc.next();

				PersonVo newperson = new PersonVo(name, hp, company);
				pList.add(newperson);
			}
			
			
			//수정하기
			else if (n == 3) {
				System.out.println("<3.수정>");
				System.out.println("> 번호 : ");
								
			} 
			
			try {
				System.out.print("번호 > ");
				int personId = sc.nextInt();
				sc.nextLine();
				
				System.out.print("이름 >  ");
				String name = sc.nextLine();
				
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				phoneDao.personUpdate1(new PersonVo(personId, name, hp, company));
				//이 부분의 의미는?
				
			} catch(InputMismatchException e) {
				System.out.println("[ 잘못 입력하셨습니다.] ");
				sc.nextLine();
			}
			

			// 삭제하기
			 if (n == 4) {
				System.out.println("<4. 삭제>");
				System.out.println("> 번호 : ");
				int minus = sc.nextInt();		//-1 쓰지 않아도 자바는 무조건 1부터 시작함
				
				phoneDao.remove(minus);

				System.out.println("삭제되었습니다.");

			}

			// 검색하기
			else if (n == 5) {
				System.out.println("<5. 검색>");
				System.out.println("> 이름 : ");
				String search = sc.next();

				for (int i=0; i<pList.size(); i++) {
					int personId = pList.get(i).getPersonId();
					String name = pList.get(i).getName();
					String hp = pList.get(i).getHp();
					String company = pList.get(i).getCompany();
					
					
					if (pList.get(i).getName().contains(search))		//
						System.out.println(personId + ".\t" + name + "\t" + hp + "\t" + company);
				}

				
				// 없는메뉴
			} else {
				System.out.println("[다시 입력하세요.]");
			}
			
			sc.close();
		}
	}
}


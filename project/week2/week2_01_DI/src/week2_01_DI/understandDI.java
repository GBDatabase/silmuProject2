package week2_01_DI;

import java.util.Date;

public class understandDI {
	public static void main(String[] args) {
		Date date = new Date();
		
		System.out.println(date);
	}


public getDate(Date d) {
	Date date = d;
	System.out.println(date);
}

public class memberUse1() {
	member m1 = new Member(); //강한 결합, 바뀌었을 시 사용불가
}

}
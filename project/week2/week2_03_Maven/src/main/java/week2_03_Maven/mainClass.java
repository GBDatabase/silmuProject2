package week2_03_Maven;

import org.springframework.context.support.GenericXmlApplicationContext;

public class mainClass {
	//xml 에서 컨테이너에서 가져올 도구를 가져오는것
		public static void main(String[] args) {
			GenericXmlApplicationContext ctx = 
					new GenericXmlApplicationContext("applicationContext.xml");
			Transportation tarns = (Transportation) ctx.getBean("trans");
			trans.move();
		}
}

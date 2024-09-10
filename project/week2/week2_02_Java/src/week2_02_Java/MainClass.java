package week2_02_Java;

public class MainClass {
	public static void main(String[] args) {
		Transportation trans = new TransportationWalk();
		//Transportation trans = new TransportationBus(); //ctrl f11로 실행
 		//Transportation trans = new TransportationTrain();
		trans.move();
	}
}

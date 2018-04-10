package tedu.page;

public class B extends A{
	static int aa=100;
	public void showAA(){
		System.out.println(getAA());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		B b=new B();
		b.showAA();
	}

}

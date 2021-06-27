package test;

/**
 * @description 自定义Hello类
 * @author perfecttomcat
 * @date 2021年6月27日 下午4:08:34
 */
public class Hello {

	public static void main(String[] args) {
		// 定义一个随机数，做随机次循环判断
		int num = 7;
		for (int i = 0; i < 6; i++) {
			if (num > 5) {
				// 如果该数大于5，则自减3
				num -= 3;
			} else {
				// 否则自增2
				num += 2;
			}
		}
		System.out.println(num);// 输出为4
	}

}

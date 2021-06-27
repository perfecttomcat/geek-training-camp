package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @description Q：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个
 *              Hello.class 文件所有字节（x=255-x）处理后的文件
 * @author perfecttomcat
 * @date 2021年6月27日 下午4:08:34
 */
public class CustomerXlassLoader extends ClassLoader {

	public static void main(String[] args) throws Exception {
		CustomerXlassLoader xlassLoader = new CustomerXlassLoader();
		Class<?> clazz = xlassLoader.loadClass("Hello");

		System.out.println(clazz.getSimpleName());
		for (Method m : clazz.getDeclaredMethods()) {
			System.out.println("method name=======>" + m.getName());
		}

		// 创建该实例对象，并调用相应的hello()
		Object object = clazz.getDeclaredConstructor().newInstance();
		Method method = clazz.getMethod("hello");
		method.invoke(object);
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(new File("D://Chrome Downloads/Hello.xlass"))) {
			// 读取.xlass文件中的数据
			byte[] dataByteArray = new byte[fis.available()];
			fis.read(dataByteArray);

			// 将文件中的所有字节x转换成255-x
			byte[] classByteArray = transferBytes(dataByteArray);

			// 通知底层定义这个类
			return defineClass(name, classByteArray, 0, classByteArray.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(name, e);
		}
	}

	/**
	 * @description 将byte[]中的所有字节x转换成255-x
	 * @param dataByteArray
	 *            源数组
	 * @return
	 */
	private byte[] transferBytes(byte[] dataByteArray) {
		int dataLength = 0;
		byte[] classByteArray = new byte[dataLength = dataByteArray.length];
		for (int i = 0; i < dataLength; i++) {
			classByteArray[i] = (byte) (255 - dataByteArray[i]);
		}
		return classByteArray;
	}

}

package ref;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Refelection extends ClassLoader {
	/*
	 * A a1 = new A(1, 2, new B("string 1", 10)); A a2 = new A(3, 4, new
	 * B("string 2", 20)); copyFields(a1, a2);
	 */
	public <T> void copyFields(T from, T to) {
		for (Field f : from.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (isPrimitivish(f.getType())) {
					f.set(to, f.get(from));
				} else {
					copyFields(f.get(from), f.get(to));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isPrimitivish(Class c) {
		return c.isPrimitive() || c == String.class || c == Boolean.class || c == Byte.class || c == Short.class
				|| c == Character.class || c == Integer.class || c == Float.class || c == Double.class
				|| c == Long.class;
	}

	public String generateJsonAndToString(Object obj) {

		char[] methodNameArr = null;
		String methodName = null;
		Method method = null;

		for (Field field : obj.getClass().getDeclaredFields()) {
			// reflection을 이용해서 멤버변수의 이름을 구한다.
			// field.setAccessible(true) 하면 필드면 필드명 가져올수 있음.
			// field.get(obj); //obj안에 필드명의 값을 가져옴
			// field.getName() //필드명
			// field.set(newObj,field.get(obj)) 구 object에서 new object에 값 대입
			try {
				methodNameArr = field.getName().toCharArray();
				// 메소드 이름이 멤버변수 이름의 첫 알파벳이 대문자로 바뀐 것이라고 가정하고 필드이름을 구해서 넣어준다.
				methodNameArr[0] = (char) ((methodNameArr[0] - 'a') + 'A');
				// 첫 알파벳을 대문자로 바꿔준다.
				methodName = "get" + new String(methodNameArr);
				// 앞에 get을 추가해서 getMethod가 되도록 한다.
				try {
					method = obj.getClass().getMethod(methodName);
					// 메소드 오브젝트에 reflection을 이용해서 메소드객체를 담는다.
					// json.put(field.getName(), method.invoke(obj));
					// 메소드 실행
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		return "aaa";
	}

	public Refelection() {
		super(Refelection.class.getClassLoader());
	}

	public Refelection(ClassLoader parent) {
		super(parent);
	}

	protected Class<?> findClass(String binaryFileName, String classNameWithPackage) throws ClassNotFoundException {
		try {
			// String path =
			// "/Users/moonti/Documents/coding_study/java/BJP/out/artifacts/test/test.jar";
			// FileInputStream file = new FileInputStream(path);
			FileInputStream file = new FileInputStream(binaryFileName);
			byte[] classByte = new byte[file.available()];
			file.read(classByte);
			file.close();
			return defineClass(classNameWithPackage, classByte, 0, classByte.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new ClassNotFoundException();
		}
		return null;
	}

	public Object invokeClassMethod(String binaryFileName, String classNameWithPackage, String methodName,
			Class<?> args, Object arg) {
		Object res = null;
		try {
			Class clazz = findClass(binaryFileName, classNameWithPackage);

			System.out.println("Loaded class name: " + clazz.getName());

			// Create a new instance from the loaded class
			Constructor constructor = clazz.getConstructor();
			Object obj = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = clazz.getMethod(methodName, args);
			System.out.println("Invoked method name: " + method.getName());
			res = method.invoke(obj, arg);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void invokeClassMethod(String classNameWithPackage, String methodName) {
		try {

			// Create a new JavaClassLoader
			ClassLoader classLoader = this.getClass().getClassLoader();

			// Load the target class using its binary name
			Class clazz = classLoader.loadClass(classNameWithPackage);

			System.out.println("Loaded class name: " + clazz.getName());

			// Create a new instance from the loaded class
			Constructor constructor = clazz.getConstructor();
			Object obj = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = clazz.getMethod(methodName);
			System.out.println("Invoked method name: " + method.getName());
			method.invoke(obj);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

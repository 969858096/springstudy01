package com.lsp.springstudy01.字节码技术;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test003 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1.使用反射技术执行某方法
		Class<?> forName = Class.forName("com.itmayiedu.Test003");
		Object newInstance = forName.newInstance();
		Method method = forName.getDeclaredMethod("sum", int.class, int.class);
		method.invoke(newInstance, 1, 5);
	}


}

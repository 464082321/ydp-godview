package com.ydp.godview.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Administrator
 *
 */
public class ReflectUtils {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getBean(Map<String,Object> param , Class clazz){
		
		Object value = null;
		
		Class[] paramTypes = new Class[1];
		
		Object obj = null;
		
		try {
			//创建实例
			obj = clazz.newInstance();
			Field[] f = clazz.getDeclaredFields();
			List<Field[]> flist = new ArrayList<Field[]>();
			flist.add(f);
			
			Class superClazz = clazz.getSuperclass();
			while(superClazz != null){
				f = superClazz.getFields();
				flist.add(f);
				superClazz = superClazz.getSuperclass();
			}
			
			for (Field[] fields : flist) {
				for (Field field : fields) {
					String fieldName = field.getName();
					value = param.get(fieldName);
					if(value != null){
						paramTypes[0] = field.getType();
//						System.out.println("类型:" + field.getType().getSimpleName());
						if("Set".equals(field.getType().getSimpleName())) {
							/** 针对set方法进行反射封装 **/
//							if("Set".equals(field.getType().getSimpleName())) {
//								
//								//封装到本对象里面
//								Method method = null;
//								//调用相应对象的set方法
//								StringBuffer methodName = new StringBuffer("set");
//								methodName.append(fieldName.substring(0, 1).toUpperCase());
//								methodName.append(fieldName.substring(1, fieldName.length()));
//								method = clazz.getMethod(methodName.toString(), paramTypes);
//								
//								
//								ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
//								
//								Type[] types = parameterizedType.getActualTypeArguments();
//								Set set = new HashSet();
//								for(Type type : types) {
//									Class ptClazz = (Class) type;
//									
//									Set<Map<String, Object>> ptSet = (Set<Map<String, Object>>) value;//转到了
//									Object setChild = null;
//									
//									for(Map<String, Object> pts : ptSet) {
//										setChild = getBean(pts, ptClazz);
//										//调用set的add方法给set赋值
//										set.add(setChild);
//									}
//								}
//								method.invoke(obj, set);
//							}
							
						} else {//普通的get,set
							Method method = null;
							//调用相应对象的set方法
							StringBuffer methodName = new StringBuffer("set");
							methodName.append(fieldName.substring(0, 1).toUpperCase());
							methodName.append(fieldName.substring(1, fieldName.length()));
							
							//测试输出
							//System.out.println(paramTypes[0].getName());
							
							method = clazz.getMethod(methodName.toString(), paramTypes);
							method.invoke(obj, ConvertUtil.getValue(value.toString(), fieldName, paramTypes[0]));
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return (T)obj;
	}
	
}

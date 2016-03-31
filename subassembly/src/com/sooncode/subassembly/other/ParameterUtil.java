package com.sooncode.subassembly.other;

import java.util.Map;

import com.sooncode.subassembly.reflect.RObject;

/**
 * 参数验证 工具类
 * 
 * @author pc
 *
 */
public class ParameterUtil {
	/**
	 * 验证字符串 参数
	 * 
	 * @param a
	 * @param agrs
	 * @return 当参数中存在空(null)参数; 空字符串("") 时返回false ,否则返回true.
	 */
	public static boolean verifiString(String a, String... agrs) {
		boolean bool = true;
		if (a == null || a.trim().equals("")) {
			bool = false;
		}

		for (int i = 0; i < agrs.length; i++) {
			if (agrs[i] == null || agrs[i].trim().equals("")) {
				bool = false;
			}
		}

		return bool;

	}

	/**
	 * 验证对象参数
	 * 
	 * @param a
	 * @param agrs
	 * @return 当参数中存在空(null)参数 时返回false ,否则返回true.
	 */
	public static boolean verifiObject(Object... agrs) {
		boolean bool = true;

		for (int i = 0; i < agrs.length; i++) {
			if (agrs[i] == null) {
				bool = false;
			}
		}
		return bool;

	}
	/**
	 * 验证 object是否为空 或 其属性是否全为空
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isObjectNull(Object object) {
		// obj != null
		if (object == null) {
			return false;
		}
		// obj的属性值不全为null
		RObject rObj = new RObject(object);
		Map<String, Object> files = rObj.getFiledAndValue();
		boolean b = false;
		for (Map.Entry<String, Object> en : files.entrySet()) {
			if (en.getValue() == null) {
				b = b || false;
			} else {
				b = b || true;
			}
		}

		if (b == false) {
			return false;
		} else {
			return true;
		}
	}
	public static void main(String[] args) {
		String a = "";
		String b = "a";
		String c = null;
		Integer d = 1;
		System.out.println(verifiObject(a, b,d));
		System.out.println(verifiObject(a, b, c));
		System.out.println(verifiObject(a));
		System.out.println(verifiObject(a, d));
	}
}

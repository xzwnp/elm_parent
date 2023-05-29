package com.example.util;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;


/**
 * 比较器工具类,可以用来比较两个对象有哪些字段不同
 * 每种字段大致可以分为4种情况:
 * 新增了字段
 * 删除了字段
 * 字段类型不变,值发生变更
 * 字段类型不变,值也未发生变更
 *
 * @author ligf
 * @version V1.0
 * @since 1.0.1 2021/10/29 14:40
 */
public class ComparbleUtil {


	/**
	 * 返回所有字段
	 *
	 * @param target
	 * @return
	 */
	public static Map<String, Field> getFields(Object target) {
		if (target == null) {
			return Collections.EMPTY_MAP;
		}
		Field[] fields = target.getClass().getDeclaredFields();
		Map<String, Field> rsMap = new HashMap<>(fields.length);
		Stream.of(fields).forEach(field -> {
			field.setAccessible(true);
			rsMap.put(field.getName(), field);
		});
		return rsMap;
	}

	/**
	 * 比较两个对象属性值是否相同,如果不同返回修改过的属性信息集合,包括：字段名,原始数据值，新值，更改类型
	 *
	 * @param source 原始对象
	 * @param target 新对象
	 * @return ArrayList<ComparbleResult>  变化后的数据集合
	 */
	public static List<ComparbleResult> compareInstance(Object source, Object target) {
		List<ComparbleResult> compareResultList = new ArrayList<>();
		try {
			// 获取字段集合
			Map<String, Field> fileds_source = getFields(source);
			Map<String, Field> fields_target = getFields(target);
			// 先遍历source集合，处理两种情况：
			// source中有的,target中没有的->字段被删除
			// source中有的,target中有但是内容变化->字段内容被更新
			for (Field field : fileds_source.values()) {
				ComparbleResult comparbleResult = new ComparbleResult();
				Object v1 = field.get(source); //source的属性值
				// source中有的,target中没有的->字段被删除
				if (!fields_target.containsKey(field.getName())) {
					comparbleResult.setFieldName(field.getName());
					comparbleResult.setChangeType(DataChangeType.DELETE);
					compareResultList.add(comparbleResult);
					continue;
				}
				Object v2 = fields_target.get(field.getName()).get(target); //target的属性值
				comparbleResult.setFieldName(field.getName());
				comparbleResult.setFieldContent(v1);
				comparbleResult.setNewFieldContent(v2);
				if (v1 == null && v2 == null) {
					continue;
				}
				// source中有的,target中有但是内容变化->字段内容被更新
				if (!v1.equals(v2)) {
					comparbleResult.setChangeType(DataChangeType.UPDATE);
					compareResultList.add(comparbleResult);
					continue;
				}
			}
			// 还剩一种情况,source中没有,target有的->新增字段
			// 遍历target集合，处理这种情况
			for (Field field : fields_target.values()) {
				ComparbleResult comparbleResult = new ComparbleResult();
				if (!fileds_source.containsKey(field.getName())) {
					comparbleResult.setFieldName(field.getName());
					comparbleResult.setChangeType(DataChangeType.ADD);
					compareResultList.add(comparbleResult);
					continue;
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return compareResultList;
	}

	@Data
	public static class ComparbleResult {

		/**
		 * 变更字段
		 */
		private String fieldName;

		/**
		 * 变更前类的内容容
		 */
		private Object fieldContent;
		/**
		 * 变更后类的内容容
		 */
		private Object newFieldContent;
		/**
		 * 变更的枚举类型
		 */
		private DataChangeType changeType;

	}

	public enum DataChangeType {
		ADD(), UPDATE(), DELETE();
	}

}
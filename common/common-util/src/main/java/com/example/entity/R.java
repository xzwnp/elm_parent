package com.example.entity;

import com.example.constant.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * com.example.commonutils
 *
 * @author xzwnp
 * 2022/1/26
 * 21:56
 * Steps：
 */
@Data
@ApiModel("统一返回结果")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class R<T> {
	@ApiModelProperty("状态码")
	private int code;// 状态码
	@ApiModelProperty("提示消息")
	private String message;// 提示消息
	@ApiModelProperty("数据,可以查看该类型的介绍文档")
	//仅需要一种数据时,指定泛型为该数据类即可在swagger生成该类的描述文档
	//需要两种以上数据时,指定泛型为map<string,object>
	private T data;// 响应结果集数据

	/**
	 * 枚举类常量解析器
	 * 快速解析枚举类常量信息，解析数据并放入到标准响应类ResponseData的属性中
	 *
	 * @param enumCode
	 */
	public void parserEnum(ResultCode enumCode) {
		this.code = enumCode.getCode();// 获取枚举常量的状态码，赋值给属性
	}

	/**
	 * 定义请求成功的：状态码，描述，结果集数据
	 *
	 * @param data 传递的响应结果集数据
	 * @return 有成功状态码，描述，结果集数据的标准格式对象
	 */
	public static <T> R<T> success(T data) {
		// 创建响应标准格式对象
		R<T> responseData = new R<T>();
		// 调用转换器方法，将（成功）枚举常量解析，放入到标准响应数据中。
		responseData.parserEnum(ResultCode.SUCCESS);
		// 放入响应数据
		responseData.setData(data);
		return responseData;
	}


	public static R<Void> success() {
		// 创建响应标准格式对象
		R<Void> r = new R<>();
		// 调用转换器方法，将（成功）枚举常量解析，放入到标准响应数据中。
		r.parserEnum(ResultCode.SUCCESS);
		return r;
	}


	/**
	 * 定义请求失败的：
	 *
	 * @param enumCode 失败时传递的常见错误枚举常量
	 * @return 有失败状态码，描述，结果集可为null，也可为自定义异常信息
	 */
	public static <T> R<T> error(ResultCode enumCode, T data) {
		// 创建响应标准格式对象
		R<T> responseData = new R<T>();
		// 调用转换器方法，将（错误）枚举常量解析。
		responseData.parserEnum(enumCode);
		responseData.setData(data);

		return responseData;
	}

	public static <T> R<T> error() {
		// 创建响应标准格式对象
		R<T> responseData = new R<T>();
		// 调用转换器方法，将（错误）枚举常量解析。
		responseData.parserEnum(ResultCode.ERROR);
		return responseData;
	}

	public static <T> R<T> error(ResultCode enumCode, String message) {
		// 创建响应标准格式对象
		R<T> responseData = new R<T>();
		// 调用转换器方法，将（错误）枚举常量解析。
		responseData.parserEnum(enumCode);
		responseData.setMessage(message);
		return responseData;
	}


	/**
	 * 有成功，有失败，但是失败的状态描述不一定能全部满足需求（枚举类有限），所以，自定义方法实现自定义信息
	 *
	 * @param code    自定义的状态码
	 * @param message 自定义的错误信息
	 * @return 有失败自定义状态码，自定义描述，结果集可为null，也可为自定义异常信息
	 */
	public static <T> R<T> generator(int code, String message, T data) {
		// 创建响应标准格式对象
		R<T> responseData = new R<T>();
		responseData.setCode(code);
		responseData.setMessage(message);
		responseData.setData(data);
		return responseData;
	}

	public static class ChainedHashMap extends HashMap<String, Object> {
		@Override
		public ChainedHashMap put(String key, Object value) {
			super.put(key, value);
			return this;
		}
	}
}


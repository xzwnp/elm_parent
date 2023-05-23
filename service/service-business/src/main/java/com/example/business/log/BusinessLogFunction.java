package com.example.business.log;

import com.example.business.entity.Business;
import com.example.business.service.BusinessService;
import icu.ynu.log.annotation.LogRecordFunction;
import icu.ynu.log.annotation.LogRecordFunctionBean;

/**
 * com.example.serviceedu.log
 *
 * @author xiaozhiwei
 * 2023/3/22
 * 9:13
 */
@LogRecordFunctionBean
public class BusinessLogFunction {
	static BusinessService businessService;

	public BusinessLogFunction(BusinessService businessService) {
		BusinessLogFunction.businessService = businessService;
	}

	@LogRecordFunction("getBusinessName")
	public static String getCourseName(String businessId) {
		Business business = businessService.getById(businessId);
		return business.getBusinessName();
	}
}

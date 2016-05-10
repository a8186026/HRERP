package com.goldcow.emanage.modi.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.goldcow.emanage.modi.persist.PmPersonnelDao;
import com.goldcow.emanage.modi.service.IModiService;
import com.goldcow.sframe.util.GlobalVal;
import com.google.common.collect.Maps;

/*
 * @version 2.0
 */

@Service("selfServiceImpl")
public class ModiServiceImpl implements IModiService {
	protected static Logger log = LoggerFactory.getLogger(ModiServiceImpl.class);
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> login(String user_name, String password) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		// 这3个参数是每个接口都要传的(ekp登录的时候不用传user_id)
		form.add("interface_num", "LG_OUT_001");// 接口编号
		form.add("sys_num", "OA");// 系统编号
		form.add("user_id", "0");
		// 下边参数不同接口,参数不同
		form.add("userCode", user_name);// 用户名
		form.add("userPasswd", password);// 用户密码
		Map<String, Object> map = restTemplate.postForObject(GlobalVal.MODI_ADDRESS, form, Map.class);
		Map<String, String> result = Maps.newHashMap();
		if ((Boolean) map.get("flag")) {
			String encryptionId = (String) map.get("encryptionId"); // 获取加密的id
			result.put("success", "success");
			result.put("message", encryptionId);
		} else {
			result.put("success", "failure");
			result.put("message", (String) map.get("message"));
		}
		return result;
	}

}
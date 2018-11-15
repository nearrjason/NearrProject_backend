package com.meitaomart.sso.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.mapper.MeitaoUserMapper;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.pojo.MeitaoUserExample;
import com.meitaomart.sso.service.LoginService;
import com.meitaomart.pojo.MeitaoUserExample.Criteria;

/**
 * 用户登录处理
 * 
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MeitaoUserMapper meitaoUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Value("${SESSION_PREFIX}")
	private String SESSION_PREFIX;

	@Override
	public MeitaoResult userLogin(String usernameOrEmail, String password) {
		// 1、判断用户和密码是否正确
		// 根据用户名查询用户信息
		MeitaoUserExample example = new MeitaoUserExample();
		Criteria criteria1 = example.createCriteria();
		Criteria criteria2 = example.createCriteria();
		criteria1.andUsernameEqualTo(usernameOrEmail);
		criteria2.andEmailEqualTo(usernameOrEmail);
		example.or(criteria2);
		
		// 执行查询
		List<MeitaoUser> list = meitaoUserMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			// 返回登录失败
			return MeitaoResult.build(400, "用户名或邮箱不存在！");
		}
		// 取用户信息
		MeitaoUser user = list.get(0);
		// 判断密码是否正确
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 2、如果不正确，返回登录失败
			return MeitaoResult.build(400, "用户名或密码错误！");
		}
		// 3、如果正确生成token。
		String token = UUID.randomUUID().toString();
		// 4、把用户信息写入redis，key：token value：用户信息
		user.setPassword(null);
		jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
		// 5、设置Session的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		// 6、把token返回

		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("token", token);
		
		return MeitaoResult.ok(map);
	}

}
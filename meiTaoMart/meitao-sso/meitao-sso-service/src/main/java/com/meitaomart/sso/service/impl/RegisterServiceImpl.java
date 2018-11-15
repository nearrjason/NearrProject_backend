package com.meitaomart.sso.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.mapper.MeitaoUserMapper;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.pojo.MeitaoUserExample;
import com.meitaomart.pojo.MeitaoUserExample.Criteria;
import com.meitaomart.sso.service.RegisterService;

/**
 * 用户注册处理Service
 * 
 * @version 1.0
 */

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private MeitaoUserMapper meitaoUserMapper;

	@Override
	public MeitaoResult checkData(String param, int type) {
		// 根据不同的type生成不同的查询条件
		MeitaoUserExample example = new MeitaoUserExample();
		Criteria criteria = example.createCriteria();
		// 1：用户名 2：手机号 3：邮箱
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			return MeitaoResult.build(400, "数据类型错误");
		}
		// 执行查询
		List<MeitaoUser> list = meitaoUserMapper.selectByExample(example);
		// 判断结果中是否包含数据, 如果有数据返回true, 如果没有数据返回false
		return list!= null && list.size() > 0 ? MeitaoResult.ok(true) : MeitaoResult.ok(false);
	}

	/**
	 * 后台校验(前台已校验完成后的后台校验)
	 */
	@Override
	public MeitaoResult register(MeitaoUser user) {
		// 数据有效性校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
				|| StringUtils.isBlank(user.getEmail())) {
			return MeitaoResult.build(400, "用户数据不完整，注册失败！");
		}
		
		if (user.getUsername().length() > 20) {
			return MeitaoResult.build(400, "用户名不能超过20位！");
		}
		
		if (!user.getEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
			return MeitaoResult.build(400, "邮箱格式不正确，请输入正确的邮箱格式!");
		}
		
		if (!user.getUsername().matches("[a-zA-Z0-9@._]*")) {
			return MeitaoResult.build(400, "用户名格式不正确: 用户名只能包括:英文字母、数字、下划线或邮箱格式!");
		}
		
		// 1：用户名 2：手机号 3：邮箱
		MeitaoResult result = checkData(user.getUsername(), 1);
		if ((boolean) result.getData()) {
			return MeitaoResult.build(400, "此用户名已经被占用！", user);
		}
		result = checkData(user.getEmail(), 3);
		if ((boolean) result.getData()) {
			return MeitaoResult.build(400, "邮箱已经被占用！", user);
		}
		// 补全pojo的属性
		user.setCreatedTime(new Date());
		user.setUpdatedTime(new Date());
		// 对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		// 把用户数据插入到数据库中
		sendEmailToUser(user);
		meitaoUserMapper.insert(user);

		// 返回添加成功
		return MeitaoResult.ok(user);
	}

	private MeitaoResult sendEmailToUser(MeitaoUser user) {
		// TODO:customer email address
		String toEmail = user.getEmail();
		// set date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// subject/title
		String subject = "Welcome to Meitaomart";
		// body
		String body = "Dear " + user.getUsername() + ":\n"
				+ " Welcome to Meitaomart, you've successfully become a member of Meitaomart!\n" + "Best Wish\n"
				+ "Meitao Team\n" + dateFormat.format(date);
		// 发送邮件
		EmailUtils.sendEmail(toEmail, subject, body);
		return MeitaoResult.ok();
	}
}
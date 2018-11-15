package com.meitaomart.order.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.meitaomart.common.utils.EmailUtils;

/**
 * 全局异常处理器
 * @author anluo
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 打印控制台
		ex.printStackTrace();
		// 写日志
		logger.debug("测试输出的日志。。。。。");
		logger.info("系统发生异常了");
		logger.error("系统发生异常", ex);
		// 发邮件 发短信
		// jmail工具包
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		String subject = "系统出现异常";
		String body = sw.toString();
		EmailUtils.groupSendEmail(subject, body);
		// 显示一个错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}

}

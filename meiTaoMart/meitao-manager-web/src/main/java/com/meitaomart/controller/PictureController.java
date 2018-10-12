package com.meitaomart.controller;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.meitaomart.common.utils.FtpUtil;
import com.meitaomart.common.utils.JsonUtils;

/**
 * 图片上传的Controller
 * @author anluo
 *
 */
@Controller
public class PictureController {
	@Value("${DEFAULT_HOST}")
	private String DEFAULT_HOST;
	@Value("${DEFAULT_PORT}")
	private Integer DEFAULT_PORT;
	@Value("${DEFAULT_USERNAME}")
	private String DEFAULT_USERNAME;
	@Value("${DEFAULT_PASSWORD}")
	private String DEFAULT_PASSWORD;
	@Value("${DEFAULT_BASE_PATH}")
	private String DEFAULT_BASE_PATH;
	@Value("${DEFAULT_FILE_PATH}")
	private String DEFAULT_FILE_PATH;
	
	@Value("${NGINX_IMAGE_SERVER_PATH}")
	private String NGINX_IMAGE_SERVER_PATH;
	@Value("${NGINX_IMAGE_SERVER_PORT}")
	private String NGINX_IMAGE_SERVER_PORT;
	
	
	@RequestMapping(value="/pic/upload", produces=MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		//把图片上传到FTP服务器
		String filename = "";
		try {
			InputStream inputStream = uploadFile.getInputStream();
			filename = new String(uploadFile.getOriginalFilename().getBytes("utf-8"),"iso-8859-1");//解决中文文件名乱码
			FtpUtil.uploadFile(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_BASE_PATH, DEFAULT_FILE_PATH, filename ,
					inputStream);
			
			Map result = new HashMap<>();
			String imageUrl = "http://" + DEFAULT_HOST + ":" + NGINX_IMAGE_SERVER_PORT + NGINX_IMAGE_SERVER_PATH + filename;
			result.put("error", 0);
			result.put("url", imageUrl);
			String str = JsonUtils.objectToJson(result);
			return str;
		} catch (Exception e) {
			e.printStackTrace();

			Map result = new HashMap<>();
			String imageUrl = "http://" + DEFAULT_HOST + ":" + NGINX_IMAGE_SERVER_PORT + NGINX_IMAGE_SERVER_PATH + filename;
			result.put("error", 1);
			result.put("url", imageUrl);
			String str = JsonUtils.objectToJson(result);
			return str;
		}
				
		//得到一个图片的地址和文件名
		//补充为完整的url
		//封装到map中返回
	}
}

package cn.mtmall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.mtmall.mapper.TbItemMapper;
import cn.mtmall.pojo.TbItem;
import cn.mtmall.pojo.TbItemExample;

public class PageHelperTest {
	
	@Test
	public void testPageHelper() throws Exception {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//從容器中獲得Mapper代理對象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//執行SQL語句之前設置分頁信息使用PageHelper的startPage方法
		PageHelper.startPage(1, 10);
		//執行查詢
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分頁信息，PageInfo。 1、 總記錄數  2、 總頁數當前頁碼
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
		System.out.println(list.size());
	}
}
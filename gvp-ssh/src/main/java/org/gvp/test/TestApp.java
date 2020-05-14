package org.gvp.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wg.gvp.pojo.News;
import wg.gvp.service.INewsService;
import wg.gvp.service.impl.NewsServiceImpl;

public class TestApp {
	public static void main(String[] args) {
		
		
		
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml") ;
	
//		System.out.println("ApplicationContext ==== >" + context);
		
		INewsService ser = context.getBean("newsServiceImpl",INewsService.class) ;
//		System.out.println(context.getBean("newsServiceImpl").getClass());
//		context.getBean("newsServiceImpl")
//		System.out.println(ser.getClass());
		News vo= new News() ;
	
		vo.setContent("ttttt");
		vo.setTitle("Nihao ");
		vo.setPubdate(new Date());
		try {
			System.out.println(ser.insert(vo)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

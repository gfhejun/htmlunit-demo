package com.gf.onlyforhebe.htmlunit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gf.onlyforhebe.htmlunit.entity.CalendarDate;

public class CalendarDateUtils {
	public static List<CalendarDate> getCurrentMonthInfo(){
		WebClient webClient = null;
		List<CalendarDate> dateList = null;

		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			dateList = new ArrayList<CalendarDate>();
			webClient = new WebClient();
			
			//数据源是360万年历
			HtmlPage page = webClient.getPage("http://hao.360.cn/rili/");

			//等待页面加载完成
			for(int k = 0; k < 60; k++){
				if(!page.getElementById("M-dates").asText().equals("")){
					break;
				}
				Thread.sleep(1000);
			}

			DomNodeList<HtmlElement> htmlElements = page.getElementById("M-dates").getElementsByTagName("li");

			//判断是否假期以及是否周末
			for(HtmlElement element : htmlElements){
				CalendarDate calendarDate = new CalendarDate();
				calendarDate.setSolarDate(dateFormat.parse(element.getAttribute("date")));

				//根据实际需要从dom获取相应的class即可
				if(element.getAttribute("class").indexOf("vacation") != -1){
					calendarDate.setVacation(true);
				}
				if(element.getAttribute("class").indexOf("weekend") != -1){
					calendarDate.setWeekend(true);
				}
				dateList.add(calendarDate);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("get date from http://hao.360.cn/rili/ error~");
		}finally{
			webClient.close();
		}
		return dateList;
	}
}

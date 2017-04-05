package com.gf.onlyforhebe.htmlunit;

import java.util.List;

import com.gf.onlyforhebe.htmlunit.entity.CalendarDate;
import com.gf.onlyforhebe.htmlunit.util.CalendarDateUtils;

public class Test {
	public static void main(String[] args){
		List<CalendarDate> list = CalendarDateUtils.getCurrentMonthInfo();
		for (CalendarDate date : list){
			System.out.println("日期：" + date.getSolarDate());
			System.out.println("是否特殊假期：" + date.isVacation());
			System.out.println("是否周末：" + date.isWeekend());
		}
	}
}

package com.gf.onlyforhebe.htmlunit.entity;

import java.util.Date;

import lombok.Data;

public @Data class CalendarDate {
	Date solarDate;//公历时间
	boolean isVacation;//是否假期
	boolean isWeekend;//是否周六日
}

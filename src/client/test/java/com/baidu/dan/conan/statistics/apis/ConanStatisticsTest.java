package com.baidu.dan.conan.statistics.apis;

import java.util.Random;

import org.junit.Test;

/**
 * 
 * 接口级别的测试
 * 
 * @author liaoqiqi
 * @email liaoqiqi@baidu.com
 *
 */
public class ConanStatisticsTest {

	@Test
	public final void test() {

		long averageTime = 0;
		long totalCount = 1000000;

		for (int i = 0; i < totalCount; ++i) {

			Random random = new Random();
			int sleepTime = random.nextInt(50000);
			System.out.println("sleep time: " + sleepTime);
			averageTime += sleepTime;

			ConanStatistics.addApiConsumeTime("serviceTest", sleepTime);
		}

		System.out.println(String.format(
				"Count: %s, CumulativeTime: %s, AverageTime: %s", totalCount,
				averageTime, averageTime * 1.0 / totalCount));

		ConanStatistics.close();
	}
}

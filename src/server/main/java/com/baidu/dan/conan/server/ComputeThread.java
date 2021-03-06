package com.baidu.dan.conan.server;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.dan.conan.server.core.GlobalComputeObject;
import com.baidu.dan.conan.server.realtimewritter.RealTimeWriterHook;

public final class ComputeThread {

	private ComputeThread() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputeThread.class);

	private static Timer timer = new Timer();

	public static void run() {

		initTimer();
	}

	/**
	 * 
	 * @Description: 每5秒merge一次
	 * 
	 * @return void
	 * @author liaoqiqi
	 * @date 2013-3-4
	 */
	private static void initTimer() {

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				try {

					// 进行实时计算
					GlobalComputeObject.getInstance().merge();

					// 实时输出
					RealTimeWriterHook.getInstance().doWork();

				} catch (Exception e) {
					LOGGER.warn(e.toString());
				}
			}
		}, 1000, 5000);
	}
}

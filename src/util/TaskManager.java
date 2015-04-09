package util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class TaskManager implements ServletContextListener {
	public static final long PERIOD_TIME = 1 * 30 * 60 * 1000;
	public static final long NEWS_PERIOD_TIME = 1 * 10 * 60 * 1000;
	public static final long NO_DELAY = 0;
	public static final long DELAY = 1 * 5 * 60 * 1000;
	private Timer timer;

	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer("refresh token", true);
		timer.schedule(new RefreshToken(), NO_DELAY, PERIOD_TIME);
		//timer.schedule(new UpdateNews(), DELAY, NEWS_PERIOD_TIME);
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
	}
}
package br.com.compasso.pautas.service;

import java.util.Timer;
import java.util.TimerTask;

public class RemindTask extends TimerTask {
	
	
	
	public RemindTask(Timer timer) {
		this.timer = timer;
	}
	private Timer timer;
	@Override
	public void run() {
		System.out.println("ddd");
		timer.cancel();
		timer.purge();
	}

}

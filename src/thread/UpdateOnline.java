package thread;

import handler.User;

public class UpdateOnline extends Thread {
	
	private User user;
	
	public UpdateOnline(User user) {
		this.user = user;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(1000);
				user.updateLastOnline();
			//	System.out.println("update online nick "+user.getUsername());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;

public class ProcessingThread extends Thread{
	 	Context context;
	    private int number;

	    public ProcessingThread(Context context, int number) {
	        this.context = context;
	        this.number = number;
	    }

	    public void run() {
	        try {
	            Thread.sleep(1500);
	        } catch (InterruptedException ie) {
	            ie.printStackTrace();
	        }

	        Intent intent = new Intent("broadcast_message_action");
	        String message = Integer.toString(number);
	        intent.putExtra("broadcast_message", message);
	        context.sendBroadcast(intent);
	    }

}

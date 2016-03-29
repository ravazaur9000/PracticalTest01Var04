package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var04Service  extends Service {
	
	private ProcessingThread processingThread = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int num = intent.getIntExtra("key_sum", -1);
        processingThread = new ProcessingThread(getApplicationContext(), num);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}

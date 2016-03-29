package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01Var03ChooseNumber extends Activity {

	   private Button playButton;
	   private EditText numberText;
	   private ButtonClickListener buttonClickListener = new ButtonClickListener();
	   
	    private IntentFilter intentFilter = new IntentFilter();
	   
	   private MessageBroadcastReceiver messageBroadcastReceiver = null;
	    private class MessageBroadcastReceiver extends BroadcastReceiver {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            String message = intent.getStringExtra("broadcast_message");
	            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	            Log.d("RECEIVER", message);
	        }
	    }
	   
	   private class ButtonClickListener implements View.OnClickListener{	   
	  
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 switch(v.getId()) {
			 case R.id.playButton:
				 if(numberText.length()>0){
					 Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
	                 intent.putExtra("number_to_guess", numberText.getText().toString());
	                 startActivityForResult(intent, 2016);
				 }
				 Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
		         intent.putExtra("number", numberText.toString());
		         startService(intent);
				 break; 
			 }
		}   
	   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);
       playButton = (Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(buttonClickListener);
        numberText = (EditText)findViewById(R.id.numberText);
        
        intentFilter.addAction("broadcast_message_action");

       


        messageBroadcastReceiver =  new MessageBroadcastReceiver();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var03_choose_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

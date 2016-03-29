package ro.pub.cs.systems.eim.practicaltest01var04;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends Activity {

	private Button checkButton,generateButton,backButton;
	private EditText guessText,scoreText;
	String toCompute ;
	private int cachedGuess;
	private int cachedScore;
	int score;
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
    	
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
            case R.id.checkButton:
            	if(Integer.parseInt(toCompute) == Integer.parseInt(guessText.getText().toString()) ){
            		score++;
            		scoreText.setText(Integer.toString(score));
            	}

            	break;
            	
            case R.id.generateButton:
            	Random generator = new Random(); 
				int i = generator.nextInt(9) + 1;
				guessText.setText(Integer.toString(i));
            	break;
            	
            case R.id.backButton:
            	setResult(RESULT_CANCELED,null);
            	finish();
            	break;
			
		}
    	
		}
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var02_play);
		checkButton = (Button)findViewById(R.id.checkButton);
		generateButton = (Button)findViewById(R.id.generateButton);
		backButton = (Button)findViewById(R.id.backButton);
		guessText=(EditText)findViewById(R.id.guessText);
		scoreText=(EditText)findViewById(R.id.scoreText);
		checkButton.setOnClickListener(buttonClickListener);
		generateButton.setOnClickListener(buttonClickListener);
		backButton.setOnClickListener(buttonClickListener);
		Intent intent = getIntent();
        if (intent != null) {
            toCompute = intent.getStringExtra("number_to_guess");
            //Toast.makeText(getApplicationContext(), toCompute, Toast.LENGTH_LONG).show();
        }
        
		score=0;
        
      
	}
	
	@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        cachedGuess= savedInstanceState.getInt("cached_guess");
        cachedScore= savedInstanceState.getInt("cached_score");
        scoreText.setText(Integer.toString(cachedScore));
        guessText.setText(Integer.toString(cachedGuess));
        Toast.makeText(getApplicationContext(), cachedScore+" ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	cachedGuess=Integer.parseInt(guessText.getText().toString());
    	cachedScore=Integer.parseInt(scoreText.getText().toString());
    	
        outState.putInt("cached_guess", cachedGuess);
        outState.putInt("cached_score", cachedScore);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var02_play, menu);
		return true;
	}
	
	 @Override
	    protected void onDestroy() {
	        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
	        stopService(intent);
	        super.onDestroy();
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

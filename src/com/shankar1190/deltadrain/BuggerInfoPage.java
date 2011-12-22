package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BuggerInfoPage extends Activity {
	
	public int buggerNo;
	public Bugger displayBugger;
	public GlobalData state;
    public EditText amount;
    public EditText comments;
    public EditText loc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.bugger_info);

	    state = ((GlobalData)getApplicationContext());
	    Bundle extras = getIntent().getExtras();
	    
	    buggerNo = new Integer(extras.getInt("buggerId"));
	    
	    displayBugger = (Bugger)state.myBuggers.get(buggerNo);
	    
	    TextView tv1 = (TextView) findViewById(R.id.buggerInfoName);
	    tv1.setText(displayBugger.getName());
	    
	    int delta = displayBugger.getDelta();
	    String displayString = "";
	    if (delta < 0) {
	    	displayString = "Bugger owes you ";
	    } else {
	    	displayString = "You owe Bugger ";
	    }
	    
	    TextView tv2 = (TextView) findViewById(R.id.buggerInfoDelta);
	    tv2.setText(displayString + ((Integer)(-delta)).toString() + " bucks");
	    
	    String type = displayBugger.getType();
	    TextView tv3 = (TextView) findViewById(R.id.buggerInfoType);
	    tv3.setText(type);
	    if (type.equals("Dangerous Fellow") || type.equals("Cheap Fellow")) {
	    	tv3.setTextColor(Color.parseColor("red"));
	    } else {
	    	tv3.setTextColor(Color.parseColor("green"));
	    } 
	    
	    amount = (EditText) findViewById(R.id.EditTextAddDelta);
        comments = (EditText) findViewById(R.id.EditTextAddComment);
        loc = (EditText) findViewById(R.id.EditTextAddLocation);
	}
	
	public void viewHistory(View v) {
		// Start a new activity here
		Intent history = new Intent(BuggerInfoPage.this, TransactionHistoryPage.class);
        history.putExtra("buggerId", (int)(buggerNo));
        startActivity(history);
	}
	
	
	public void updateBugger(View v) {    
        GlobalData state = ((GlobalData)getApplicationContext());
        
        int amt;
        try {
        	amt = Integer.parseInt(amount.getText().toString());
        } catch (NumberFormatException e) {
        	amt = 0;
        }
        
        Transaction t = new Transaction(amt, comments.getText().toString(),
        								loc.getText().toString());
        displayBugger.insertTransaction(t);
        
        Gson gson = new Gson();
        String jsonNameList = gson.toJson(state.myBuggerNames);
        String jsonBuggerList = gson.toJson(state.myBuggers);
        
        SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString("names", jsonNameList);
        editor.putString("buggers", jsonBuggerList);
        
        editor.commit();
        
        startActivity(getIntent()); finish();
	}
	
	public void buggerResetDelta(View v) {
		displayBugger.resetDelta();
		startActivity(getIntent()); finish();
	}
}
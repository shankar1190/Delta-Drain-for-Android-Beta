package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TransactionHistoryPage extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_history);
        
        GlobalData state = ((GlobalData)getApplicationContext());
        Bundle extras = getIntent().getExtras();
        int buggerNo = extras.getInt("buggerId");
        
        TextView tv = (TextView) findViewById(R.id.tr1);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(((Bugger)state.myBuggers.toArray()[buggerNo]).getLastTenTransactions());
	}
}
package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DeltaDrainforAndroid extends Activity {
    ArrayList<String> buggerNames;
    ArrayList<Bugger> buggers;
    
    String buggerList;
    String nameList;
    
    ArrayAdapter<String> adapter;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String nameList = settings.getString("names", "NULL");
        String buggerList = settings.getString("buggers", "NULL");
        
        // settings.edit().clear().commit();
        
        GlobalData state = ((GlobalData)getApplicationContext());
        buggerNames = state.myBuggerNames;
        buggers = state.myBuggers;
        
        if (nameList != "NULL" && buggerList != "NULL") {
		    Gson gson = new Gson();
		    Type arrayListType1 = new TypeToken<ArrayList<String>>() {}.getType();
		    Type arrayListType2 = new TypeToken<ArrayList<Bugger>>() {}.getType();
		    
			state.myBuggerNames = (gson.fromJson(nameList, arrayListType1));
			state.myBuggers = (gson.fromJson(buggerList, arrayListType2));
	    }
        
        adapter = new ArrayAdapter<String> (this, R.layout.list_item, state.myBuggerNames);
        ListView lv = (ListView)findViewById(R.id.buggerList);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	Intent buggerInfoPage = new Intent(DeltaDrainforAndroid.this, BuggerInfoPage.class);
                buggerInfoPage.putExtra("buggerId", position);
                startActivity(buggerInfoPage);
            }
          });
    }
    
    public void addBugger(View v) {
    	final EditText nameField = (EditText) findViewById(R.id.EditTextBuggerName);
        final EditText amount = (EditText) findViewById(R.id.EditTextAmount);
        final EditText comments = (EditText) findViewById(R.id.EditTextComments);
        final EditText loc = (EditText) findViewById(R.id.EditTextLocation);
        final Spinner typeSpinner = (Spinner) findViewById(R.id.SpinnerBuggerType);
        	
        Bugger newBugger = new Bugger(nameField.getText().toString(),
        							  Integer.parseInt(amount.getText().toString()),
        							  comments.getText().toString(),
        							  typeSpinner.getSelectedItem().toString(),
        							  loc.getText().toString());
        
        GlobalData state = ((GlobalData)getApplicationContext());
        buggerNames = state.myBuggerNames;
        buggers = state.myBuggers;
        
        buggers.add(newBugger);
        buggerNames.add(new String(nameField.getText().toString()));
          
        Gson gson = new Gson();
        String jsonNameList = gson.toJson(buggerNames);
        String jsonBuggerList = gson.toJson(buggers);
        
        SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString("names", jsonNameList);
        editor.putString("buggers", jsonBuggerList);
        
        editor.commit();
        
        adapter.notifyDataSetChanged();
    }
}
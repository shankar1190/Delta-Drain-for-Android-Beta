package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

import java.util.ArrayList;

import android.app.Application;

public class GlobalData extends Application {
	// List of Buggers' Names
    public ArrayList<String> myBuggerNames = new ArrayList<String>(500);
    
    // List of Bugger details
    public ArrayList<Bugger> myBuggers = new ArrayList<Bugger>(500);
}

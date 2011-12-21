package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 * ... Testing Git ... 20 Dec 2011 5:52 PM 
 */

import java.util.ArrayList;

import android.app.Application;

public class GlobalData extends Application {
	// List of Buggers' Names
    public ArrayList<String> myBuggerNames = new ArrayList<String>(50);
    
    // List of Bugger details
    public ArrayList<Bugger> myBuggers = new ArrayList<Bugger>(50);
}

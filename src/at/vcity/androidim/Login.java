package at.vcity.androidim;


import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class Login extends Messenger {	

    protected static final int NOT_CONNECTED_TO_SERVICE = 0;
	protected static final int FILL_BOTH_USERNAME_AND_PASSWORD = 1;
	public static final String AUTHENTICATION_FAILED = "0";
	public static final String FRIEND_LIST = "FRIEND_LIST";
	protected static final int MAKE_SURE_USERNAME_AND_PASSWORD_CORRECT = 2 ;
	protected static final int NOT_CONNECTED_TO_NETWORK = 3;
	private EditText usernameText;
    private EditText passwordText;
    private Button cancelButton;
 
    public static final int SIGN_UP_ID = Menu.FIRST;
    public static final int EXIT_APP_ID = Menu.FIRST + 1;
   
    //UsersDAO usersdb = new UsersDAO(this);
   // public   UsersDAO usersdb = new UsersDAO(this);
   
    
    
    /** Called when the activity is first created. */	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
		
	   this.start();
               
        setContentView(R.layout.login_screen);
        setTitle("Login");
        
        Button loginButton = (Button) findViewById(R.id.login);
        cancelButton = (Button) findViewById(R.id.cancel_login);
        usernameText = (EditText) findViewById(R.id.userName);
        passwordText = (EditText) findViewById(R.id.password);        
        
       loginButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) 
		
			{					
				//if(usersdb.checkIfExist(new User("hajar","hajar"))){System.out.println("hajar existe");}
				if (usernameText.length()>0 && passwordText.length() >0){
					// checking if the login exists in the database
				
					User user = new User(usernameText.getText().toString(),passwordText.getText().toString());
				//	String a = usernameText.getText().toString();
				//	System.out.println("login insere : "+ a);
				//	String b = passwordText.toString();
				//	System.out.println("password insere : "+ b);
					//usersdb.open();
					if ( usersdb.checkIfExist(user) ) {
						
						Toast.makeText(getApplicationContext(),R.string.user_authenticated_success, Toast.LENGTH_LONG).show();
						Intent i = new Intent(Login.this, FriendList.class);																
						startActivity(i);
						
					}
				
				else{
					Toast.makeText(getApplicationContext(),R.string.make_sure_username_and_password_correct, Toast.LENGTH_LONG).show();}
				}
				//usersdb.close();
			}       	
		
        });
        
        cancelButton.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) 
			{					
				
				finish();
				
			}
        	
        });
        
        
    }
    





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		boolean result = super.onCreateOptionsMenu(menu);
		
		 menu.add(0, SIGN_UP_ID, 0, R.string.sign_up);
		 menu.add(0, EXIT_APP_ID, 0, R.string.exit_application);


		return result;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
	    
		switch(item.getItemId()) 
	    {
	    	case SIGN_UP_ID:
	    		Intent i = new Intent(Login.this, SignUp.class);
	    		
	    		startActivity(i);
	    		return true;
	    	case EXIT_APP_ID:
	    		cancelButton.performClick();
	    		return true;
	    }
	       
	    return super.onMenuItemSelected(featureId, item);
	}

	
	
    
    
    
    
    
}
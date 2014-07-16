package at.vcity.androidim;

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
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SignUp extends Messenger  {
	
	private static final int FILL_ALL_FIELDS = 0;
	protected static final int TYPE_SAME_PASSWORD_IN_PASSWORD_FIELDS = 1;
	private static final int SIGN_UP_FAILED = 2;
	private static final int SIGN_UP_USERNAME_CRASHED = 3;
	private static final int SIGN_UP_SUCCESSFULL = 4;
	protected static final int USERNAME_AND_PASSWORD_LENGTH_SHORT = 5;
	
	
//	private static final String SERVER_RES_SIGN_UP_FAILED = "0";
	private static final String SERVER_RES_RES_SIGN_UP_SUCCESFULL = "1";
	private static final String SERVER_RES_SIGN_UP_USERNAME_CRASHED = "2";
	
	
	
	private EditText usernameText;
	private EditText passwordText;
	private EditText eMailText;
	private EditText passwordAgainText;

    
	private Handler handler = new Handler();
	
	


	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);    
	        this.start();
	    
	               
	        setContentView(R.layout.sign_up_screen);
	        setTitle("Sign up");
	        
	        Button signUpButton = (Button) findViewById(R.id.signUp);
	        Button cancelButton = (Button) findViewById(R.id.cancel_signUp);
	        usernameText = (EditText) findViewById(R.id.userName);
	        passwordText = (EditText) findViewById(R.id.password);  
	        passwordAgainText = (EditText) findViewById(R.id.passwordAgain);  
	        eMailText = (EditText) findViewById(R.id.email);

	        signUpButton.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) 
				{						
					if (usernameText.length() > 0 &&		
						passwordText.length() > 0 && 
						passwordAgainText.length() > 0 &&
						eMailText.length() > 0 
						)
						
					{   
						if(passwordText.getText().toString().equals(passwordAgainText.getText().toString())){
							
						 	  if(usernameText.length()>=5 && passwordText.length()<=5){
						 		  //Check if the login is already taken
						 		  if(!usersdb.checkLoginTaken(usernameText.getText().toString())){
						 	
								   //TODO check email address is valid
						 			User newUser = new User(usernameText.getText().toString(),passwordText.getText().toString());
						 			usersdb.open();
						 			usersdb.add(newUser);
						 			Toast.makeText(getApplicationContext(),R.string.signup_successfull, Toast.LENGTH_LONG).show();
						 			//usersdb.close();
							      }
						 	  
						 		  else{Toast.makeText(getApplicationContext(),R.string.signup_username_crashed, Toast.LENGTH_LONG).show();}
						}
						     else{Toast.makeText(getApplicationContext(),R.string.username_and_password_length_short, Toast.LENGTH_LONG).show();}
						}
					
						else{Toast.makeText(getApplicationContext(),R.string.signup_type_same_password_in_password_fields, Toast.LENGTH_LONG).show();}
					    
				   }
					
				   else{Toast.makeText(getApplicationContext(),R.string.signup_fill_all_fields, Toast.LENGTH_LONG).show();}
				}
					       	
	        });
	        
	        cancelButton.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) 
				{						
					finish();					
				}	        	
	        });
	        
	        
	    }





	


}

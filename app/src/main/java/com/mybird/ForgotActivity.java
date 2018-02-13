package com.mybird;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Config.ConstValue;
import SenderEmail.Mail;
import util.Common;
import util.ConnectionDetector;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi") 
public class ForgotActivity extends ActionBarActivity {
	public SharedPreferences settings;
	public ConnectionDetector cd;
	EditText txtPhone;
	Button btnLogin;
	String deviceid;
	Common common;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot);
		getSupportActionBar().hide();
       common = new Common();
		
		settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
		cd=new ConnectionDetector(this);
		
		txtPhone = (EditText)findViewById(R.id.editPhone);

		TextView backforgot = (TextView) findViewById(R.id.backforgot);
		backforgot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplication(),LoginActivity.class));
			}
		});
		
		btnLogin = (Button)findViewById(R.id.buttonLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(txtPhone.getText().toString().length()==0)
				{
					txtPhone.setError("Masukan Email Anda");
				}
				else{
						new loginTask().execute(true);
					}
				// TODO Auto-generated method stub
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	class loginTask extends AsyncTask<Boolean, Void, String>{


		String temp_password;
		String phone;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			 phone = txtPhone.getText().toString();
			dialog = ProgressDialog.show(ForgotActivity.this, "Loading", "Sedang Proses...", true);
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(String result) {
			if(result != null){
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}else{
				if(temp_password!=null && !temp_password.equalsIgnoreCase("")) {
					//Getting intent and PendingIntent instance

//					SmsManager smsManager = SmsManager.getDefault();
//					smsManager.sendTextMessage(txtPhone.getText().toString(), null, "Your reset temp password is : " +temp_password, null, null);


//					Intent emailIntent = new Intent();
//					emailIntent.setData(Uri.parse("from:" + "Rafolufofficial@gmail.com"));
//
//					emailIntent.setData(Uri.parse("mailto:" + txtPhone.getText().toString() ));
//					emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Bird");
//					emailIntent.putExtra(Intent.EXTRA_TEXT,"Your reset temp password is : " +temp_password);
//
//					startActivity(emailIntent);

//					Mail m = new Mail("rafligoodramadhan@gmail.com", "rafligood");
//
//					String[] toArr = {txtPhone.getText().toString() };
//					m.setTo(toArr);
//					m.setFrom("rafligoodramadhan@gmail.com");
//					m.setSubject("MyBird password Account");
//					m.setBody("Your reset temp password is : " +temp_password);
//
//					try {
//						if(m.send()) {
//							Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
//						} else {
//							Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
//						}
//					} catch(Exception e) {
//						Log.e("MailApp", "Could not send email", e);
//					}


					Toast.makeText(getApplicationContext(), "Periksa Email Untuk Menerima Password baru", Toast.LENGTH_SHORT).show();
					finish();
				}
			}
			// TODO Auto-generated method stub
			dialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
		@Override
		protected void onCancelled(String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
		
		@Override
		protected String doInBackground(Boolean... params) {
			String responceString = null;

			List<NameValuePair> nameVapluePairs = new ArrayList<NameValuePair>(2);
			nameVapluePairs.add(new BasicNameValuePair("email",phone));
//			nameVapluePairs.add(new BasicNameValuePair("email",phone));

			JSONObject jObj = common.sendJsonData(ConstValue.JSON_FORGOT, nameVapluePairs);
			try{
				if(jObj.getString("responce").equalsIgnoreCase("success")){
					temp_password = jObj.getString("data");
//					Mail m = new Mail("rafligoodramadhan@gmail.com", "rafligood");
//
//					String[] toArr = {phone };
//					m.setTo(toArr);
//					m.setFrom("rafligoodramadhan@gmail.com");
//					m.setSubject("MyBird password Account");
//					m.setBody("Your reset temp password is : " +temp_password);
				}
				else{
					responceString = jObj.getString("error");
				}
			}
			catch(JSONException e){
				responceString = e.getMessage();
			}
			// TODO Auto-generated method stub

			Mail m = new Mail("mybirdsilobur@gmail.com", "mybird1234");

			String[] toArr = {phone };
			m.setTo(toArr);
			m.setFrom("mybirdsilobur@gmail.com");
			m.setSubject("MyBird password Account");
			m.setBody("Your reset temp password is : " +temp_password);

			try {
				if(m.send()) {
					Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
				}
			} catch(Exception e) {
				Log.e("MailApp", "Could not send email", e);
			}
			return responceString;
		}
		
				
	}
	}


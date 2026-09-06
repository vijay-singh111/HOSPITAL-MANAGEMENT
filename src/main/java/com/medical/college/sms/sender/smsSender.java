package com.medical.college.sms.sender;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 

import java.net.*;

public class smsSender {

	
	public static String user,pass,sender,stype,msg,priority,retval=""; 
	public smsSender()
	{
	user = "MTECHL";
	pass = "erdirector"; 
	sender = "MTECHL"; 
	msg = " Registation Success ";
	priority="ndnd"; 
	stype="normal";
	
	}
	
	public boolean sendSms(String mobile )
	{
	try
	{
	String smsurl="http://trans.smsfresh.co/api/sendmsg.php";
	String data = URLEncoder.encode("user", "UTF-8") + " = "+URLEncoder.encode(user, "UTF-8");
	data += "&" + URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
	data += "&" + URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");
	data += "&" + URLEncoder.encode("text", "UTF-8")+"="+URLEncoder.encode(msg, "UTF-8");
	data += "&" + URLEncoder.encode("sender", "UTF-8")+"="+URLEncoder.encode(sender, "UTF-8");
	data += "&" + URLEncoder.encode("priority", "UTF-8")+"="+URLEncoder.encode(priority, "UTF-8");
	data += "&" + URLEncoder.encode("stype", "UTF-8")+"="+URLEncoder.encode(stype, "UTF-8"); 
	URL url = new URL(smsurl);
	URLConnection con =url.openConnection(); 
	con.setDoOutput(true);
	//Pushthe Message
	OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream()); 
	wr.write(data);
	wr.flush();
	//Get the response
	BufferedReader rd=new BufferedReader(new 
	InputStreamReader(con.getInputStream())); String line; 
	while((line=rd.readLine())!=null)
	{
	retval+=line;
	}
	wr.close();
	rd.close(); 
	return true;
	}
	catch(Exception ex)
	{
	return false;
	}
	}


}

package gov.nist.sip.proxy.project;

import java.sql.*;
import javax.sip.header.FromHeader;
import javax.sip.header.ToHeader;
import javax.sip.message.Request;
import gov.nist.sip.proxy.Proxy;

public class ProxyDecorator extends SipListenerDec {
	
	Proxy proxy;
	Blocker blocker;
	ConnectToDB database;
	
	public ProxyDecorator(Proxy myproxy) {
		super(myproxy);
		this.database = new ConnectToDB();
		this.blocker = new Blocker(database);
	}
	
	public String getUserNamefromHeader(String header)
	{
		String[] parts = header.split("@");
		String[] parts2 = parts[0].split("<");
		String[] parts3 = parts2[1].split(":");
		return parts3[1];	
	}
	
	public String getCallerName(Request request){
		String head = request.getHeader(FromHeader.NAME).toString();
		String callerName = getUserNamefromHeader(head);	
		return callerName;
	}
	
	public String getCalleeName(Request request){
		String head = request.getHeader(ToHeader.NAME).toString();
		String calleeName = getUserNamefromHeader(head);	
		return calleeName;
	}
	
	public boolean checkBlocking(Request request)	{
		String caller = getCallerName(request);
		String callee = getCalleeName(request);
		boolean result = blocker.checkBlock(caller, callee);
		return result;
	}
}

package gov.nist.sip.proxy.project;

import java.io.Console;
import java.sql.*;
import java.text.ParseException;

import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.ToHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import gov.nist.sip.proxy.Proxy;



public class ProxyDecorator extends SipListenerDec {
	
	Proxy proxy;
	Blocker blocker;
	ConnectToDB database;
	Forwarder forwarder;
	
	public ProxyDecorator(Proxy myproxy) {
		super(myproxy);
		this.database = new ConnectToDB();
		this.blocker = new Blocker(database);
		this.forwarder = new Forwarder(database);
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
	
	public String findForwardTarget (Request request){
		String caller = getCallerName(request);
		String callee = getCalleeName(request);	
		String forwardee = forwarder.getTarget(caller,callee);
		
		return forwardee;
	}
	
	public boolean checkloops (Request request){
		boolean result = false;
		String check = findForwardTarget(request);
		if ( (check.equals("loop_detected")) || (check.equals("error")) ) {
			result =true;
		}
		return result;
	}
	
	public Request forwardRequest(Request request, AddressFactory adfactory, HeaderFactory headfactory){
		String target = findForwardTarget(request);
//		System.out.println(request.getRequestURI().toString()+" sexy kkontog\n");
		SipURI test = (SipURI) request.getRequestURI();
		try {
			test.setUser(target);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Address newAddress = adfactory.createAddress(test) ;
		ToHeader toHeader;
        try {
			toHeader = headfactory.createToHeader(newAddress, null);
			Request newRequest = (Request) request.clone();
			newRequest.setHeader(toHeader);
	        return newRequest;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return request;
		}
	
	}
}

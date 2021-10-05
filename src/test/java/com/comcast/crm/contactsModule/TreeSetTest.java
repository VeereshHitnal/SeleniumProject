package com.comcast.crm.contactsModule;

import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {


		TreeSet ts=new TreeSet();
		//ts.add(null);Null Pointer Exception
		ts.add(10);
		ts.add(25);
		ts.add(13);
		ts.add(20);
		//ts.add("Veer");ClassCastException 
		ts.add(120);
		//ts.add(120);Duplcates are not allowed
		ts.add(30);
		
		System.out.println(ts.descendingSet());

	}

}

package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.RowFilter.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Main {
	static TreeMap<Date, String> map= new TreeMap<Date, String>();
	static TreeMap<Date, String> mapCopy= new TreeMap<Date, String>();
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException, ParseException {
		Gson gson= new GsonBuilder()
				.setPrettyPrinting()
				.create();
		Jobtitle jobtitle=gson.fromJson(new FileReader("src/main/resources/jobtitle.json"), Jobtitle.class);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String res="";
		System.out.println("Json");
		for (Job job : jobtitle.getJobtitle()) {
			String[] str=job.getJobtitlename().split(" ");
			for (int i = 0; i < str.length-1; i++) {
				res+=str[i];
			}
			System.out.println(format.parse(job.getStarttime())+" "+ res);
			map.put(format.parse(job.getStarttime()), res);
			res="";
		}
		System.out.println("\nMap");
		for (Map.Entry<Date, String> entry : map.entrySet()) {
			Date key = entry.getKey();
			String val = entry.getValue();
			if(!val.equals(res)) {
				System.out.println(key+" "+val);
				mapCopy.put(key, val);
				res = val;
			}
		}
		System.out.println("\nAnswer");
		
		last();
	}
	
	public static void last() {
		Map.Entry<Date, String> val= mapCopy.lastEntry();
		System.out.println("Start time: "+val.getKey()+"\tJob title name: "+val.getValue());
	}
	
	public static List<Date> select(String work) {
		List<Date> list=new ArrayList<Date>();
		for (Map.Entry<Date, String> entry : mapCopy.entrySet()) {
			Date key = entry.getKey();
			String val = entry.getValue();
			if(val.equals(work)) {
				list.add(key);
			}
		}
		return list;
	}

}

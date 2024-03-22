package com;

import java.util.Date;

public class Job {
	String jobtitlename;
	String starttime;

	public Job(String jobtitlename, String starttime) {
		this.jobtitlename = jobtitlename;
		this.starttime = starttime;
	}
	
	public String getJobtitlename() {
		return jobtitlename;
	}
	public void setJobtitlename(String jobtitlename) {
		this.jobtitlename = jobtitlename;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
}

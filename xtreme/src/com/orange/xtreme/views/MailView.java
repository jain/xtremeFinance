package com.orange.xtreme.views;

public interface MailView {
		String getUsername();
		String getEmail();
		void invalid();
		void send();
}

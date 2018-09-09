package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	@Autowired
	protected MessageSource messageSource;

	protected final String getAppMessage(String mesgKey) {
		return messageSource.getMessage(mesgKey, null, LocaleContextHolder.getLocale());
	}
}

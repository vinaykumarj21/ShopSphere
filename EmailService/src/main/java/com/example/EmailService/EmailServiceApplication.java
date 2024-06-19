package com.example.EmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
/*
.\bin\windows\kafka-server-start.bat .\config\server.properties -> running kafka server command

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties-> running zookeeper command

Run commands on two different cmd prompt
*/

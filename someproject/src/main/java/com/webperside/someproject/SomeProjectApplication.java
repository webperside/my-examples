package com.webperside.someproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SomeProjectApplication {

	/* Project Logic
	User:
	1. See user's data / accounts / if account has no money, don't show it in the list
	1.1 See user's accounts (even if, it has no money)
	2. Approving user / requires to have some money in any account
	3. Create new user with account data

	Account:
	1. Delete account by id (Deleting is possible if the account has no money)
	2. Account to account
		| money transferring (is possible if the account has enough money)
		| send notification to received user about transferring

	 */

	public static void main(String[] args) {
		SpringApplication.run(SomeProjectApplication.class, args);
	}

}

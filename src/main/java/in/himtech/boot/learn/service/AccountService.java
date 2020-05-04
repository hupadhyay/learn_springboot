package in.himtech.boot.learn.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import in.himtech.boot.learn.repo.AccountRepo;

public class AccountService {
	@Autowired
	private AccountRepo repo;
	
	public int getTotalAmount() {
		int[] stmt = repo.getAccountStmt("1001");
		return Arrays.stream(stmt).sum();
	}

}

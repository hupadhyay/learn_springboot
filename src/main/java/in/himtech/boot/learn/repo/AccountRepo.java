package in.himtech.boot.learn.repo;

import org.springframework.stereotype.Component;

@Component
public class AccountRepo {

	public int[] getAccountStmt(String accntNum){
		return new int[]{5, 4, 9};
	}
}

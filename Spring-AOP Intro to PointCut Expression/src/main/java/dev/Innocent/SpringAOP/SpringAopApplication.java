package dev.Innocent.SpringAOP;

import dev.Innocent.SpringAOP.DAO.AccountDAO;
import dev.Innocent.SpringAOP.DAO.MembershipDAO;
import dev.Innocent.SpringAOP.Model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){
		return runner -> {
			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		// Create an account object
		Account theAccount = new Account();
		// Call the business method
		theAccountDAO.addAccount(theAccount, true);

		// Call the membership business method
		theMembershipDAO.addAccount();

		// do it again
		System.out.println("\n let's call it again! \n");

		// Call the business method again
		theAccountDAO.addAccount(theAccount, true);

		theAccountDAO.doWork();
		theMembershipDAO.goToSleep();
	}
}

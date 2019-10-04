package com.gestorinc;

import com.gestorinc.repository.IBankRepository;
import com.gestorinc.repository.entity.CredencialesBancarias;
import com.gestorinc.repository.entity.CredencialesBancariasPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SavingsFundContributionsApplication implements CommandLineRunner {

	@Autowired
	private IBankRepository bankRepository;

	public static void main(String[] args) {
		SpringApplication.run(SavingsFundContributionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*CredencialesBancarias credencialesBancarias =
				new CredencialesBancarias(new CredencialesBancariasPK(1, "BAN", "BAN01"), "BANCO1",
						"667a9876b3d88f1de7a51cbbf39c63e8999aa82b6ef28dc66f872e0c986c9434106388dcf94aaa3855923d58f650cbb14d34edba47c7db53347cdad84a217285".getBytes());
		bankRepository.save(credencialesBancarias);
		credencialesBancarias =
				new CredencialesBancarias(new CredencialesBancariasPK(1, "BAN", "BAN02"), "BANCO2",
						"5a8b021d7c8ee10112c2398c39d146bc6f93c82e91572fcc4e542379e52363b30fc1d17a1f8edb0b42f804417318be3c4f73aeec49e92f5a89a482e7e10446c9".getBytes());
		bankRepository.save(credencialesBancarias);*/
	}
}

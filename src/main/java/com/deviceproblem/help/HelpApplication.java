package com.deviceproblem.help;

import com.deviceproblem.help.model.User;
import com.deviceproblem.help.model.UserType;
import com.deviceproblem.help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class HelpApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpApplication.class, args);
	}
	@Autowired
	private UserRepository userRepository;


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void run(String... strings) throws Exception {
		String email = "vigenhovhannisyan@hotmail.com";
		User oneByEmail = userRepository.findOneByEmail(email);
		if (oneByEmail == null) {
			userRepository.save(User.builder()
					.email(email)
					.password(new BCryptPasswordEncoder().encode("vig123"))
					.name("admin")
					.surname("admin")
					.userType(UserType.ADMIN)
					.build());

		}
	}

}

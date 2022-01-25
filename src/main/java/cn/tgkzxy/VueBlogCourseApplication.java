package cn.tgkzxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling //开启定时功能
public class VueBlogCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueBlogCourseApplication.class, args);
	}

}

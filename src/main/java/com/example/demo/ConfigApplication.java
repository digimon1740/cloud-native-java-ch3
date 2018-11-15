package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//https://cloud.spring.io/spring-cloud-config/
//https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html
//https://github.com/spring-cloud-samples/configserver 이 샘플코드를 참조
//https://github.com/spring-cloud-samples/config-repo/blob/master/application.yml 이 설정파일을 읽어옴
@EnableConfigServer
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}

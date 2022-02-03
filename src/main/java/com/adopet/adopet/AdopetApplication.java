package com.adopet.adopet;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdopetApplication {
	@Value("localhost")
	private String host;

	@Value("8082")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);
		return new SocketIOServer(config);
	}
	public static void main(String[] args) {
		SpringApplication.run(AdopetApplication.class, args);
	}

}

package com.eyhong.barcode.scanner;

import com.eyhong.barcode.scanner.model.MainScreenGUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@Slf4j
public class ScannerApplication implements ApplicationRunner {

	@Autowired
	private MainScreenGUI mainScreenGUI;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ScannerApplication.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(ApplicationArguments arg) {
		log.info("Beginning of ScannerApplication. Arg length is: {}", arg.getSourceArgs().length);
		mainScreenGUI.displayUI();
	}
}


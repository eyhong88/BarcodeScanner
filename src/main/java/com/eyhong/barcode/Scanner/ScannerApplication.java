package com.eyhong.barcode.Scanner;

import com.eyhong.barcode.Scanner.model.InventoryScannerUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ScannerApplication implements ApplicationRunner {

	@Autowired
	private InventoryScannerUI ui;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ScannerApplication.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(ApplicationArguments arg) {
		ui.displayUI();
	}
}


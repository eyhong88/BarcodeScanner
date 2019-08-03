package com.eyhong.barcode.scanner;

import com.eyhong.barcode.scanner.factory.InventoryScannerFactory;
import com.eyhong.barcode.scanner.model.InventoryScannerPrice;
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

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ScannerApplication.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(ApplicationArguments arg) {
		log.info("Beginning of ScannerApplication. Arg length is: {}", arg.getSourceArgs().length);

		String app = arg.getSourceArgs()[0];
		ApplicationEnum appId;
		try {
			 appId = ApplicationEnum.valueOf(app);
		}
		catch (NumberFormatException e){
			//Default to the price scan app.
			appId = ApplicationEnum.SCAN;
		}

		log.info("Application being run is: {}", appId);

		InventoryScannerFactory.getInstance(appId).displayUI();

	}
}


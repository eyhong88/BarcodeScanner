package com.eyhong.barcode.scanner.factory;

import com.eyhong.barcode.scanner.ApplicationEnum;
import com.eyhong.barcode.scanner.model.InventoryScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryScannerFactory {

    @Autowired
    private List<InventoryScanner> services;

    private static final Map<Integer, InventoryScanner> serviceMap = new HashMap<>();

    /**
     *
     */
    @PostConstruct
    public void initServiceCache(){
        for(InventoryScanner service : services){
            serviceMap.put(service.getType().getAppId(), service);
        }
    }


    /**
     *
     * @param appEnum
     * @return
     */
    public static InventoryScanner getInstance(ApplicationEnum appEnum) {
        return serviceMap.get(appEnum.getAppId());
    }


}

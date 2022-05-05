package no.sintef.giot.bhp.core;

import java.util.ServiceLoader;
import org.apache.log4j.Logger;

public class MainClass {
	
	final static Logger logger = Logger.getLogger(MainClass.class);

	public static void main(String[] args) {
		
				
		//search for load data implementations
        ServiceLoader<LoadDataService> ldLoader = ServiceLoader.load(LoadDataService.class);
        System.out.println("Found LoadDataService implementations: ");
        for (LoadDataService ld : ldLoader) {
        	logger.debug(ld.getClass().toString());
        	ld.getDataAsJson();
        }

        //search for inference implementations
        ServiceLoader<InferenceService> inLoader = ServiceLoader.load(InferenceService.class);
        System.out.println("Found InferenceService implementations: ");
        for (InferenceService in : inLoader) {
        	logger.debug(in.getClass().toString());
        	in.infer("");
        }

	}

}

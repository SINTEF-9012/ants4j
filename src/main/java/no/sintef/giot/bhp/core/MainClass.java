package no.sintef.giot.bhp.core;

import java.util.ServiceLoader;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//search for load data implementations
        ServiceLoader<LoadDataService> ldLoader = ServiceLoader.load(LoadDataService.class);
        System.out.println("Found LoadDataService implementations: ");
        for (LoadDataService ld : ldLoader) {
        	System.out.println(ld.getClass().toString());
        	ld.getDataAsJson();
            //Log.i(TAG, ld.getDataAsCSV());
            //Log.i(TAG, ld.getDataAsJson());
        }

        //search for inference implementations
        ServiceLoader<InferenceService> inLoader = ServiceLoader.load(InferenceService.class);
        System.out.println("Found InferenceService implementations: ");
        for (InferenceService in : inLoader) {
        	System.out.println(in.getClass().toString());
        	in.infer("");
        }

	}

}

package no.sintef.giot.bhp.core;

/**
 * An abstract class for creating different inference implementations
 */
public abstract class InferenceService {

	/**
	 * Runs the inference
	 * 
	 * @param inputData input data as string
	 * @return inference results
	 */
	public abstract String infer(String inputData);
}

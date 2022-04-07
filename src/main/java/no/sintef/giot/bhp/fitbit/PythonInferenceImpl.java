package no.sintef.giot.bhp.fitbit;

import org.python.util.PythonInterpreter;

import no.sintef.giot.bhp.core.InferenceService;

public class PythonInferenceImpl extends InferenceService {

	private static final String TAG = "PythonInferenceImpl";

	@Override
	public String infer(String inputData) {

		try (PythonInterpreter pyInterp = new PythonInterpreter()) {
			pyInterp.exec("print('Hello Python World!')");
		}
		
		return "";

		/*
		 * Python py = Python.getInstance();
		 * 
		 * // Obtain the system's input stream (available from Chaquopy) PyObject sys =
		 * py.getModule("sys"); PyObject io = py.getModule("io"); // Obtain the right
		 * python module PyObject module = py.getModule("inference");
		 * 
		 * // Redirect the system's output stream to the Python interpreter PyObject
		 * textOutputStream = io.callAttr("StringIO"); sys.put("stdout",
		 * textOutputStream);
		 * 
		 * // Create a string variable that will contain the standard output of the
		 * Python interpreter String interpreterOutput = "";
		 * 
		 * // Execute the Python code try { // TODO: read the contents either from CSV
		 * file or from the database and pass them to Python as a CSV string argument
		 * //String csvData = DBHelper.getInstance(this).getAllEntriesAsCsv();
		 * module.callAttr("infer_from_string", inputData);
		 * 
		 * //module.callAttrThrows("main", "hrv.csv"); interpreterOutput =
		 * textOutputStream.callAttr("getvalue").toString(); } catch (PyException e) {
		 * // If there's an error, you can obtain its output as well // e.g. if you
		 * mispell the code // Missing parentheses in call to 'print' // Did you mean
		 * print("text")? // <string>, line 1 interpreterOutput =
		 * e.getMessage().toString(); }
		 * 
		 * // Outputs the results: Log.i(TAG, "RESULTS FROM INFERENCE: " +
		 * interpreterOutput);
		 * 
		 * return interpreterOutput; }
		 */
	}

}

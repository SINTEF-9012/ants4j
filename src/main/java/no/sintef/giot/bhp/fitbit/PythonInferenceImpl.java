package no.sintef.giot.bhp.fitbit;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.apache.log4j.Logger;
import org.python.util.PythonInterpreter;

import no.sintef.giot.bhp.core.InferenceService;

public class PythonInferenceImpl extends InferenceService {
	
	final static Logger logger = Logger.getLogger(PythonInferenceImpl.class);

	@Override
	public String infer(String inputData) {
		
		ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\rustemd\\eclipse-workspace\\ants4j\\src\\main\\resources\\python\\inference.py", "C:\\Users\\rustemd\\eclipse-workspace\\ants4j\\src\\main\\resources\\python\\hrv.csv");
	    processBuilder.redirectErrorStream(true);

	    Process process;
		try {
			process = processBuilder.start();
			int exitCode = process.waitFor();
		    System.out.println(exitCode);
		    System.out.println(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8));
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
//		ScriptEngineManager manager = new ScriptEngineManager();
//	    List<ScriptEngineFactory> factories = manager.getEngineFactories();
//	    for (ScriptEngineFactory factory : factories) {
//	    	System.out.println("============");
//	      System.out.println(factory.getEngineName());
//	      System.out.println(factory.getEngineVersion());
//	      System.out.println(factory.getLanguageName());
//	      System.out.println(factory.getLanguageVersion());
//	      System.out.println(factory.getExtensions());
//	      System.out.println(factory.getMimeTypes());
//	      System.out.println(factory.getNames());
//	    }
//	    
//	    StringWriter writer = new StringWriter();
//	    ScriptContext context = new SimpleScriptContext();
//	    context.setWriter(writer);
//
//	    ScriptEngine engine = manager.getEngineByName("python");
//	    try {
//			engine.eval(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("python/inference.py")), context);
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    //assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", writer.toString().trim());
//
//		try (PythonInterpreter pyInterp = new PythonInterpreter()) {
//			pyInterp.exec("print('Hello Python World!')");
//			pyInterp.exec("import os");
//			pyInterp.exec("print os.path");
//			
//			InputStream is = getClass().getClassLoader().getResourceAsStream("python/inference.py");
//			pyInterp.execfile(is);
//		}
		
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

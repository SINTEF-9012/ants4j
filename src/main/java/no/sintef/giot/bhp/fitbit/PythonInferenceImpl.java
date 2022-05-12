package no.sintef.giot.bhp.fitbit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
//import org.python.util.PythonInterpreter;
import no.sintef.giot.bhp.core.InferenceService;

public class PythonInferenceImpl extends InferenceService {
	
	final static Logger logger = Logger.getLogger(PythonInferenceImpl.class);

	/**
	 *
	 */
	@Override
	public String infer(String inputData) {
		
//		String line = "python " + getClass().getClassLoader().getResource("python/inference.py").getPath() + " " + getClass().getClassLoader().getResource("python/hrv.csv").getPath();
//	    CommandLine cmdLine = CommandLine.parse(line);
//	        
//	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
//	        
//	    DefaultExecutor executor = new DefaultExecutor();
//	    executor.setStreamHandler(streamHandler);
//
//	    try {
//			int exitCode = executor.execute(cmdLine);
//		} catch (IOException e1) {
//			logger.error(e1.getMessage());
//			e1.printStackTrace();		
//		}
		
		logger.info("Working Directory = " + System.getProperty("user.dir"));
		
		// Root directory
        String rootDir = System.getProperty("user.dir");
 
        try {
            // using `Files.walk()` method
            Files.walk(Paths.get(rootDir), 2)
                    //.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
			    
	    final File script = new File(getClass().getClassLoader().getResource("python/inference.py").getFile());
	    final File data = new File(getClass().getClassLoader().getResource("python/hrv.csv").getFile());
	    
	    final ProcessBuilder pb = new ProcessBuilder();
	    pb.redirectErrorStream(true);
	    //pb.command("python", script.getPath(), data.getPath());	    
	    
	    pb.command("python3", "/python/inference.py", "/python/hrv.csv");	  
	    	    
	    try {
	        //Process p = installScriptBuilder.start();
	        String output = IOUtils.toString(pb.start().getInputStream(), StandardCharsets.UTF_8);
	        logger.info(output);
	    } catch (IOException e) {
	        logger.error(e.getMessage());
	    }	    
		
//		ScriptEngineManager manager = new ScriptEngineManager();	    	    
//	    StringWriter writer = new StringWriter();
//	    ScriptContext context = new SimpleScriptContext();
//	    context.setWriter(writer);
//	    
//	    ScriptEngine engine = manager.getEngineByName("python");
//	    //engine.eval(new FileReader(resolvePythonScriptPath("hello.py")), context);
//	    engine.put(ScriptEngine.ARGV, "");
//	    try {
//			engine.eval(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("python/inference.py")), context);
//			 Log.info(writer.toString().trim());
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return "";
	}

}

package example;

import java.io.IOException;

import wrap.DataProcessingWrapper;
import wrap.InputWrapper;
import wrap.OutputWrapper;

public class Template {
	public static void main(String[] args) throws Exception{
		
		//input : console, file,  http 
		InputWrapper iw = new InputWrapper(InputWrapper.iType.CONSOLE,InputWrapper.dType.STRING);
		String res= iw.<String>getData("","");
		// data : file, http type : json, text... 
		DataProcessingWrapper dsw = new DataProcessingWrapper();
		dsw.<String>prepareData(res);
		dsw.solve();
		dsw.genResponse();
		
		//output : file, http
		OutputWrapper ow = new OutputWrapper(OutputWrapper.oType.FILE);
		ow.writeDoc("path","name","result");
		
	}
}

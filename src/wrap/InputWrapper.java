package wrap;

import java.io.FileNotFoundException;
import java.io.IOException;

import file.GLineNumberReader;
import file.Reader;
import http.server.JettyServer;
import input.ConsoleScanner;

public class InputWrapper {
	public enum iType{
		CONSOLE{
	        public Object create() { return (Object) new ConsoleScanner();}
	    },
		FILE{
	        public Object create() { return (Object) new Reader();}
	    },
		FILE_WITH_LINE{
	        public Object create() { return (Object) new GLineNumberReader();}
	    },
		HTTP{
	        public Object create() { return (Object) new JettyServer();}
	    };
		public abstract Object create();
	}
	
	public enum dType{
		STRING,
		INT,
		DOUBLE,
		LIST,
		JSON,
	}
	
	private iType _iType;
	private dType _dType;
	private Object reader;
	
	public InputWrapper(iType inputType, dType dataType) throws Exception {
		this._iType=inputType;
		this._dType=dataType;
		
		reader = this._iType.create();
		
		if(this._iType==iType.HTTP)
			new JettyServer().start();
	}

	public <T> T getData(String path,String name) throws Exception {
		T result = null;
		switch (this._iType) {
		case CONSOLE:
			switch (this._dType) {
				case STRING:
					result = (T)((ConsoleScanner)reader).readLine();
					break;
				case INT:
					result = (T)((ConsoleScanner)reader).readInt();
					break;
				case DOUBLE:
					result = (T)((ConsoleScanner)reader).readDouble();
					break;
				default:
					throw new Exception("Not Found console data dype");
			}
			break;
		case FILE:
			((Reader)reader).openFile(path, name);
			result = (T)((Reader)reader).readAllLine();
			break;
		case FILE_WITH_LINE:
			((GLineNumberReader)reader).openFile(path, name);
			result = (T)((Reader)reader).readAllLine();
			break;
		default:
			throw new Exception("Not Found reader typer");
			
		}
		return (T)result;
	}
	

}

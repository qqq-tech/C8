package wrap;

import java.util.List;

import org.eclipse.jetty.http.HttpMethod;

import file.GLineNumberReader;
import file.Reader;
import file.Writer;
import http.client.JettyClient;
import http.client.JettyMClient;
import http.server.JettyServer;
import input.ConsoleScanner;
import wrap.InputWrapper.dType;
import wrap.InputWrapper.iType;

public class OutputWrapper {
	public enum oType {
		FILE {
			public Object create() {
				return (Object) new Writer();
			}
		},
		HTTP {
			public Object create() {
				return (Object) new JettyClient();
			}
		};
		public abstract Object create();
	}

	private Object writer;

	private oType _oType;

	public OutputWrapper(oType outputType) throws Exception {
		this._oType = outputType;
		writer = this._oType.create();
	}

	public void writeDoc(String path, String name, String docs) throws Exception {
		write(path, name, docs);
	}

	public void writeDoc(String path, String name, List<String> docs) throws Exception {
		write(path, name, docs);
	}

	public void writeDoc(String endpoint, HttpMethod method, String data) throws Exception {
		if(this._oType == oType.HTTP)
			((JettyClient) writer).sClient(endpoint, method, data);
		else
			throw new Exception("Not HTTP Type");
	}
	
	private void write(String path, String name, Object docs) throws Exception {
		switch (this._oType) {
		case FILE:
			((Writer) writer).openFile(path, name);
			if (docs instanceof List) {
				((Writer) writer).writeLine((List<String>) docs);
			} else if (docs instanceof String) {
				((Writer) writer).writeLine((String) docs);
			}
			else {
				throw new Exception("Not Found writer typer");
			}
			break;
		default:
			throw new Exception("Not Found writer typer");
		}
	}
}

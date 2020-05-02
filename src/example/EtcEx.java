package example;

import java.util.HashMap;
import java.util.Map;

import etc.DirSearch;
import etc.Validator;

public class EtcEx {

	public static void main(String[] args) {

		DirSearch.subDirList("C:\\Hyper-V");
		Map<String, HashMap<String, String>> res= DirSearch.result;
		for(String k : res.keySet())
		{
			HashMap<String, String> r = res.get(k);
			System.out.println("filename:"+k);
			for(String k2: r.keySet())
			{
				System.out.println(k2+":"+r.get(k2));
			}
		}
		
		String email = "@ggg.com";
		System.out.println(Validator.isEMail(email));
		
		String ip = "192.168.40.255";
		System.out.println(Validator.isIPv4(ip));
	}

}

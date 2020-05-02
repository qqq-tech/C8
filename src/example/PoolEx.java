package example;

import pool.GThreadPool;

public class PoolEx {
	public static void main(String[] args) {
		GThreadPool exam = new GThreadPool(2);
	
		for(int i=0;i<100;i++)
		{
			exam.doWork("4", i+"");
		}
		exam.finish();
	}

}

package pool;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GThreadPool {

	private ExecutorService executorService;

	public GThreadPool(int count) {
		// executorService =
		// Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		executorService = Executors.newFixedThreadPool(count);
	}

	private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
		@Override
		public void completed(Integer result, Void attachment) {
			System.out.println("completed() 실행: " + result);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			System.out.println("failed() 실행: " + exc.toString());
		}
	};

	public void simpleWork(Runnable task) {

		// Runnable runnable = new Runnable() {
		// @Override
		// public void run() {
		// // 스레드에게 시킬 작업 내용
		// ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
		// int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		// String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름
		// 얻기
		// System.out.println("[총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
		// // 일부로 예외 발생 시킴
		// int value = Integer.parseInt("예외");
		// }
		// };

		// 스레드풀에게 작업 처리 요청
		// executorService.execute(task);
		executorService.submit(task);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void doWork(final String x, final String y) {
		//return 값 필요시 callable
//		Callable<T> task = new Callable<T>() {
//		    @Override
//		    public T call() throws Exception {
//		        // Code
//		        return T;
//		    }
//		};

//		Callable<Integer> task = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int sum = 0;
//                
//                for (int i = 1; i <= 10; i++) {
//                    sum += i;
//                }
//                
//                return sum;
//            }
//        };


		//리턴값 필요 없을시
		Runnable task = new Runnable() {
			public void run() {
				try {
					int intX = Integer.parseInt(x);
					int intY = Integer.parseInt(y);

					int result = intX + intY;
					callback.completed(result, null);

				} catch (NumberFormatException e) {
					callback.failed(e, null);
				}
			}
		};
		doWork(task, x, y);
		//doWork(()->{}, x, y);
	}

	public void doWork(Runnable task, final String x, final String y) {
		//작업할것
		executorService.submit(task);
		//Future<T> future=executorService.submit(task);
		//Future<Integer> future = executorService.submit(task);
		//T result = future.get();
	}

	public void finish() {
		executorService.shutdown();
	}

}

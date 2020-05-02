package example;

public class CriticalSection {
	int count;

	public int increase() {
		synchronized (this) {
			return ++count;
		}
	}

	public synchronized int increase2() {
		return ++count;
	}

}

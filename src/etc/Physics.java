package etc;

public class Physics {
	// 속도계산
	public static double cal_speed(double dist, double time) {
		System.out.print("\n Distance(km) : " + dist);
		System.out.print("\n Time(hr) : " + time);

		return dist / time;
	}

	// 주행거리 계산
	public static double cal_dis(double speed, double time) {
		System.out.print("\n Time(hr) : " + time);
		System.out.print("\n Speed(km / hr) : " + speed);

		return speed * time;
	}

	// 남은 시간 계산
	public static double cal_time(double dist, double speed) {
		System.out.print("\n Distance(km) : " + dist);
		System.out.print("\n Speed(km / hr) : " + speed);

		return dist / speed;
	}
}

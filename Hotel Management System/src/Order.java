import java.util.Collections;
import java.util.Comparator;

public class Order {

	public void orderName() {
//		Main m = new Main();
		Collections.sort(Main.vguest, new Comparator<Guest>() {

			public int compare(Guest o1, Guest o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public void orderID() {
		Collections.sort(Main.vguest, new Comparator<Guest>() {

			public int compare(Guest o1, Guest o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
	}

	public void orderCheckIn() {
		Collections.sort(Main.vguest, new Comparator<Guest>() {

			public int compare(Guest o1, Guest o2) {
				return o1.getCheckin().compareTo(o2.getCheckin());
			}
		});
	}

	public void orderCheckOut() {
		Collections.sort(Main.vguest, new Comparator<Guest>() {

			public int compare(Guest o1, Guest o2) {
				return o1.getCheckout().compareTo(o2.getCheckout());
			}
		});
	}

}

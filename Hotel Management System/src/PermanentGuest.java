
public class PermanentGuest extends Guest {
	
	public PermanentGuest(String name, String birth, String id, String facility, String checkin, String checkout,
			String roomType, long rentFee, int status) {
		super(name, birth, id, facility, checkin, checkout, roomType);
		this.rentFee = rentFee;
		this.status = status;
	}
	
	public PermanentGuest()
	{
		super();
	}

	protected long rentFee;
	protected int status;

	public long getRentFee() {
		return rentFee;
	}

	public void setRentFee(long rentFee) {
		this.rentFee = rentFee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

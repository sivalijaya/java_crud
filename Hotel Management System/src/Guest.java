
public class Guest {
	protected String name;
	protected String birth;
	protected String id;
	protected String facility;
	protected String checkin;
	protected String checkout;
	protected String roomType;

	public Guest(String name, String birth, String id, String facility, String checkin,
			String checkout, String roomType) {
		super();
		this.name = name;
		this.birth = birth;
		this.id = id;
		this.facility = facility;
		this.checkin = checkin;
		this.checkout = checkout;
		this.roomType = roomType;
	}
	
	public Guest()
	{
		
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

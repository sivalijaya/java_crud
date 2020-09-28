import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/* 
 * Hotel Management System
 * 
 * -Sivalijaya Fernedi Jordan	2201746026
 * -Nicholas Surya				2201816051
 * -Ghirlandaio					2201816101
 * -Stanley Anthony Muktar		2201744323
 * -Naomi Belinda May Arbai		2201814286
 */

public class Main {
	public static Scanner scan = new Scanner(System.in);
	public static Vector<Guest> vguest = new Vector<Guest>();
	static int totalroom = 0;

	private static void menu() {
		cls();
		System.out.println("Room Booked: " + totalroom);

		System.out.println("Hotel Management System");
		System.out.println("=======================");
		System.out.println("1. Input Guess Data");
		System.out.println("2. View Guess Data");
		System.out.println("3. Update Guess Data");
		System.out.println("4. Delete Guess Data");
		System.out.println("5. Exit");
		System.out.printf("Choose Your Choice: ");
	}

	private static void cls() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	private static int scanInt() {
		int result = -1;

		try {
			result = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Input Must be Digit");
		} finally {
			scan.nextLine();
		}

		return result;
	}

	private void writeFile(String filename) {
		try (FileWriter fileWriter = new FileWriter(filename, false)) {
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for (int i = 0; i < vguest.size(); i++) {
				if (vguest.get(i) instanceof PermanentGuest) {
					PermanentGuest temp = (PermanentGuest) vguest.get(i);
					writer.write(temp.getId() + "," + temp.getName() + "," + temp.getBirth() + "," + temp.getCheckin()
							+ "," + temp.getCheckout() + "," + temp.getRoomType() + "," + temp.getFacility() + ","
							+ temp.getRentFee() + "," + temp.getStatus());
				} else if (vguest.get(i) instanceof RegularGuest) {
					RegularGuest temp = (RegularGuest) vguest.get(i);
					writer.write(temp.getId() + "," + temp.getName() + "," + temp.getBirth() + "," + temp.getCheckin()
							+ "," + temp.getCheckout() + "," + temp.getRoomType() + "," + temp.getFacility() + "," + "0"
							+ "," + "0");
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFile(String filename) {
		try (FileReader fileReader = new FileReader(filename)) {
			BufferedReader reader = new BufferedReader(fileReader);

			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data[7].equals("0")) {
					vguest.add(new RegularGuest(data[1], data[2], data[0], data[6], data[3], data[4], data[5]));
				} else {
					long rentfee = Long.parseLong(data[7]);
					int status = Integer.parseInt(data[8]);
					vguest.add(new PermanentGuest(data[1], data[2], data[0], data[6], data[3], data[4], data[5],
							rentfee, status));
				}
				totalroom += 1;
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Main() {
		String filename = "GuestData.txt";
		readFile(filename);
		int choose = 0;
		do {
			menu();
			choose = scanInt();

			switch (choose) {
			case 1:
				inputData();
				break;
			case 2:
				viewData();
				break;
			case 3:
				updateData();
				break;
			case 4:
				deleteData();
				break;
			case 5:
				System.out.println("Thanks For Using Our Application");
				break;
			}
		} while (choose != 5);
		writeFile(filename);
	}

	private void updateData() {
		int index = -1;
		Order sort = new Order();
		sort.orderName();
		if (totalroom == 0) {
			System.out.println("No Room!");
			scan.nextLine();
		} else {
			for (int i = 0; i < vguest.size(); i++) {
				if (vguest.get(i) instanceof PermanentGuest) {
					PermanentGuest temp = (PermanentGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Status    : " + temp.status + " years");
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Rent Fee  : Rp." + temp.rentFee);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				} else if (vguest.get(i) instanceof RegularGuest) {
					RegularGuest temp = (RegularGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				}
				System.out.println();
			}

			do {
				System.out.print("Input Number do you want to update [1-" + totalroom + "]: ");
				index = scanInt();
			} while (index < 1 || index > totalroom);

			RoomType roomtypes = new RoomType();
//			Random rand = new Random();
			cls();

			// Guest type
			int type = 0;
			do {
				System.out.println("Select Type Guest");
				System.out.println("1. Permanent Guest");
				System.out.println("2. Regular Guest");
				System.out.print("Choose: ");
				type = scanInt();
			} while (type < 1 || type > 2);

			// Random ID
//		String randomHuruf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		String randomAngka = "1234567890";
//		String id = "";
//		do {
//			for (int i = 0; i < 3; i++) {
//				id += randomHuruf.charAt(rand.nextInt(randomHuruf.length()));
//			}
//			for (int j = 0; j < 5; j++) {
//				id += randomAngka.charAt(rand.nextInt(randomAngka.length()));
//			}
//		} while (IDValidation(id) == true);

			// Input Nama
			String name;
			do {
				System.out.print("Input Guess Name: ");

				name = scan.nextLine();
			} while (name.length() == 0);

			// Input Tanggal Lahir
			String birth;
			int year, month, date;
			do {

				System.out.print("Input Birthday [dd/mm/yyyy ex 01/01/2001]: ");
				birth = scan.nextLine();
			} while (dateValidation(birth) == false);

			// Check-in
			String checkin;
			do {
				System.out.print("Check-in Date [dd/mm/yyyy]: ");
				checkin = scan.nextLine();
			} while (dateValidation(checkin) == false);

			// Check-out
			String checkout = "";
			if (type == 2) {
				do {
					System.out.print("Check-out Date [dd/mm/yyyy]: ");
					checkout = scan.nextLine();
				} while (checkout.equals(checkin) || dateValidation(checkout) == false);
			}

			// Input Room
			String roomtype;
			do {
				System.out.print("Input Room Type [Classic || Duluxe || Executive || Superior]: ");
				roomtype = scan.nextLine();
			} while (!roomtype.equals("Classic") && !roomtype.equals("Duluxe") && !roomtype.equals("Executive")
					&& !roomtype.equals("Superior"));

			// Input Facility
			String facility;
			do {
				System.out.print("Input Facility [ '-' to skip]: ");
				facility = scan.nextLine();
			} while (facility.isEmpty());

			int yearly = 0;
			long rentfee = 0;
			int date1, month1, year1;
			if (type == 1) {
				// rent fee annually
				do {
					System.out.print("Input long rent [>=1]: ");
					yearly = scanInt();
				} while (yearly < 1);

				if (roomtype.equals("Classic")) {
					rentfee = (long) (((roomtypes.classic * 365 * yearly) - (0.25 * yearly * roomtypes.classic)));
				} else if (roomtype.equals("Duluxe")) {
					rentfee = (long) (((roomtypes.duluxe * 365 * yearly) - (0.25 * yearly * roomtypes.duluxe)));
				} else if (roomtype.equals("Executive")) {
					rentfee = (long) (((roomtypes.executive * 365 * yearly) - (0.25 * yearly * roomtypes.executive)));
				} else if (roomtype.equals("Superior")) {
					rentfee = (long) (((roomtypes.superior * 365 * yearly) - (0.25 * yearly * roomtypes.superior)));
				}
				System.out.println(rentfee);
				Calendar cal = Calendar.getInstance();
				String[] s1 = checkin.split("/");
				date = Integer.parseInt(s1[0]);
				month = Integer.parseInt(s1[1]) - 1;
				year = Integer.parseInt(s1[2]);
				cal.set(year, month, date);
				cal.add(Calendar.YEAR, yearly);

				checkout = date + "/" + month + "/" + (year + yearly);

				cls();
				System.out.println("ID        : " + vguest.get(index).getId());
				System.out.println("Name      : " + name);
				System.out.println("Birth     : " + birth);
				System.out.println("Check-in  : " + checkin);
				System.out.println("Status    : " + yearly + " years");
				System.out.println("Rent Fee  : Rp." + rentfee);
				System.out.println("Check-out : " + checkout);
				System.out.println("Room Type : " + roomtype);
				System.out.println("Facility  : " + facility);
			}

			else if (type == 2) {
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();

				String[] s1 = checkin.split("/");
				date = Integer.parseInt(s1[0]);
				month = Integer.parseInt(s1[1]) - 1;
				year = Integer.parseInt(s1[2]);

				String[] s2 = checkout.split("/");
				date1 = Integer.parseInt(s2[0]);
				month1 = Integer.parseInt(s2[1]) - 1;
				year1 = Integer.parseInt(s2[2]);

				cal1.set(year, month, date);
				cal2.set(year1, month1, date1);

				Date one = cal1.getTime();
				Date two = cal2.getTime();
				long days = 0;
				days = daysBetween(one, two);

				if (roomtype.equals("Classic")) {
					rentfee = (int) (roomtypes.classic * days);
				} else if (roomtype.equals("Duluxe")) {
					rentfee = (int) (roomtypes.duluxe * days);
				} else if (roomtype.equals("Executive")) {
					rentfee = (int) (roomtypes.executive * days);
				} else if (roomtype.equals("Superior")) {
					rentfee = (int) (roomtypes.superior * days);
				}

				cls();
				System.out.println("ID        : " + vguest.get(index).getId());
				System.out.println("Name      : " + name);
				System.out.println("Birth     : " + birth);
				System.out.println("Check-in  : " + checkin);
				System.out.println("Check-out : " + checkout);
				System.out.println("long Rent : " + days + " nights");
				System.out.println("Room Type : " + roomtype);
				System.out.println("Facility  : " + facility);
				System.out.println("Rent Fee  : Rp." + rentfee);
			}

			String valid;
			do {
				System.out.print("Are you sure want to input this data? [Y/N]: ");
				valid = scan.nextLine();
				if (valid.equals("Y")) {
					if (type == 1) {
						PermanentGuest guestedit = new PermanentGuest();
						guestedit.setName(name);
						guestedit.setBirth(birth);
						guestedit.setCheckin(checkin);
						guestedit.setStatus(yearly);
						guestedit.setRentFee(rentfee);
						guestedit.setCheckout(checkout);
						guestedit.setFacility(facility);
						guestedit.setRoomType(roomtype);

					} else if (type == 2) {
						RegularGuest guestedit = new RegularGuest();
						guestedit.setName(name);
						guestedit.setBirth(birth);
						guestedit.setCheckin(checkin);
						guestedit.setCheckout(checkout);
						guestedit.setFacility(facility);
						guestedit.setRoomType(roomtype);
					}
					totalroom += 1;
					System.out.println("Data Updated!");
					scan.nextLine();
				} else if (valid.equals("N")) {
					return;
				}
			} while (valid.equals("Y") && valid.equals("N"));
		}
	}

	private void deleteData() {
		int index = -1;
		Order sort = new Order();
		sort.orderName();
		if (totalroom == 0) {
			System.out.println("No Room!");
			scan.nextLine();
		} else {
			for (int i = 0; i < vguest.size(); i++) {
				if (vguest.get(i) instanceof PermanentGuest) {
					PermanentGuest temp = (PermanentGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Status    : " + temp.status + " years");
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Rent Fee  : Rp." + temp.rentFee);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				} else if (vguest.get(i) instanceof RegularGuest) {
					RegularGuest temp = (RegularGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				}
				System.out.println();
			}

			do {
				System.out.print("Input Number do you want to delete [1-" + totalroom + "]: ");
				index = scanInt();

				// Delete data
			} while (index < 1 || index > totalroom);
			vguest.remove(index - 1);
			totalroom -= 1;
			System.out.println("Data Removed!");
			scan.nextLine();
		}
	}

	private void viewData() {
		Order sort = new Order();
		int choose = 0;
		if (totalroom == 0) {
			System.out.println("No Room!");
			scan.nextLine();
		} else {
			System.out.println("Sort By");
			System.out.println("1. Guest Name");
			System.out.println("2. Guest ID");
//			System.out.println("3. Guest Check-in");
//			System.out.println("4. Guest Check-out");
			choose = scanInt();

			if (choose == 1) {
				sort.orderName();
			} else if (choose == 2) {
				sort.orderID();
			}
//		else if (choose == 3) {
//			sort.orderCheckIn();
//		} else if (choose == 4) {
//			sort.orderCheckOut();
//		}

			for (int i = 0; i < vguest.size(); i++) {
				if (vguest.get(i) instanceof PermanentGuest) {
					PermanentGuest temp = (PermanentGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Status    : " + temp.status + " years");
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Rent Fee  : Rp." + temp.rentFee);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				} else if (vguest.get(i) instanceof RegularGuest) {
					RegularGuest temp = (RegularGuest) vguest.get(i);
					System.out.println((i + 1) + ".");
					System.out.println("ID        : " + temp.id);
					System.out.println("Name      : " + temp.name);
					System.out.println("Birth     : " + temp.birth);
					System.out.println("Check-in  : " + temp.checkin);
					System.out.println("Check-out : " + temp.checkout);
					System.out.println("Room Type : " + temp.roomType);
					System.out.println("Facility  : " + temp.facility);
				}
				System.out.println();
			}
			scan.nextLine();
		}
	}

	public static void main(String[] args) {
		new Main();
	}

	private static boolean dateValidation(String date) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		try {

			df.parse(date);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	private static boolean IDValidation(String id) {
		for (int i = 0; i < vguest.size(); i++) {
			if (id.equals(vguest.get(i).id)) {
				return true;
			}
		}

		return false;
	}

	private static void inputData() {
		RoomType roomtypes = new RoomType();
		Random rand = new Random();
		cls();

		// Guest type
		int type = 0;
		do {
			System.out.println("Select Type Guest");
			System.out.println("1. Permanent Guest");
			System.out.println("2. Regular Guest");
			System.out.print("Choose: ");
			type = scanInt();
		} while (type < 1 || type > 2);

		// Random ID
		String randomHuruf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomAngka = "1234567890";
		String id = "";
		do {
			for (int i = 0; i < 3; i++) {
				id += randomHuruf.charAt(rand.nextInt(randomHuruf.length()));
			}
			for (int j = 0; j < 5; j++) {
				id += randomAngka.charAt(rand.nextInt(randomAngka.length()));
			}
		} while (IDValidation(id) == true);

		// Input Nama
		String name;
		do {
			System.out.print("Input Guess Name: ");

			name = scan.nextLine();
		} while (name.length() == 0);

		// Input Tanggal Lahir
		String birth;
		int year, month, date;
		do {

			System.out.print("Input Birthday [dd/mm/yyyy ex 01/01/2001]: ");
			birth = scan.nextLine();
		} while (dateValidation(birth) == false);

		// Check-in
		String checkin;
		do {
			System.out.print("Check-in Date [dd/mm/yyyy]: ");
			checkin = scan.nextLine();
		} while (dateValidation(checkin) == false);

		// Check-out
		String checkout = "";
		if (type == 2) {
			do {
				System.out.print("Check-out Date [dd/mm/yyyy]: ");
				checkout = scan.nextLine();
			} while (checkout.equals(checkin) || dateValidation(checkout) == false);
		}

		// Input Room
		String roomtype;
		do {
			System.out.print("Input Room Type [Classic || Duluxe || Executive || Superior]: ");
			roomtype = scan.nextLine();
		} while (!roomtype.equals("Classic") && !roomtype.equals("Duluxe") && !roomtype.equals("Executive")
				&& !roomtype.equals("Superior"));

		// Input Facility
		String facility;
		do {
			System.out.print("Input Facility [ '-' to skip]: ");
			facility = scan.nextLine();
		} while (facility.isEmpty());

		int yearly = 0;
		long rentfee = 0;
		int date1, month1, year1;
		if (type == 1) {
			// rent fee annually
			do {
				System.out.print("Input long rent [>=1]: ");
				yearly = scanInt();
			} while (yearly < 1);

			if (roomtype.equals("Classic")) {
				rentfee = (long) (((roomtypes.classic * 365 * yearly) - (0.25 * yearly * roomtypes.classic)));
			} else if (roomtype.equals("Duluxe")) {
				rentfee = (long) (((roomtypes.duluxe * 365 * yearly) - (0.25 * yearly * roomtypes.duluxe)));
			} else if (roomtype.equals("Executive")) {
				rentfee = (long) (((roomtypes.executive * 365 * yearly) - (0.25 * yearly * roomtypes.executive)));
			} else if (roomtype.equals("Superior")) {
				rentfee = (long) (((roomtypes.superior * 365 * yearly) - (0.25 * yearly * roomtypes.superior)));
			}
			System.out.println(rentfee);
			Calendar cal = Calendar.getInstance();
			String[] s1 = checkin.split("/");
			date = Integer.parseInt(s1[0]);
			month = Integer.parseInt(s1[1]) - 1;
			year = Integer.parseInt(s1[2]);
			cal.set(year, month, date);
			cal.add(Calendar.YEAR, yearly);

			checkout = date + "/" + month + "/" + (year + yearly);

			cls();
			System.out.println("ID        : " + id);
			System.out.println("Name      : " + name);
			System.out.println("Birth     : " + birth);
			System.out.println("Check-in  : " + checkin);
			System.out.println("Status    : " + yearly + " years");
			System.out.println("Rent Fee  : Rp." + rentfee);
			System.out.println("Check-out : " + checkout);
			System.out.println("Room Type : " + roomtype);
			System.out.println("Facility  : " + facility);
		}

		else if (type == 2) {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			String[] s1 = checkin.split("/");
			date = Integer.parseInt(s1[0]);
			month = Integer.parseInt(s1[1]) - 1;
			year = Integer.parseInt(s1[2]);

			String[] s2 = checkout.split("/");
			date1 = Integer.parseInt(s2[0]);
			month1 = Integer.parseInt(s2[1]) - 1;
			year1 = Integer.parseInt(s2[2]);

			cal1.set(year, month, date);
			cal2.set(year1, month1, date1);

			Date one = cal1.getTime();
			Date two = cal2.getTime();
			long days = 0;
			days = daysBetween(one, two);

			if (roomtype.equals("Classic")) {
				rentfee = (int) (roomtypes.classic * days);
			} else if (roomtype.equals("Duluxe")) {
				rentfee = (int) (roomtypes.duluxe * days);
			} else if (roomtype.equals("Executive")) {
				rentfee = (int) (roomtypes.executive * days);
			} else if (roomtype.equals("Superior")) {
				rentfee = (int) (roomtypes.superior * days);
			}

			cls();
			System.out.println("ID        : " + id);
			System.out.println("Name      : " + name);
			System.out.println("Birth     : " + birth);
			System.out.println("Check-in  : " + checkin);
			System.out.println("Check-out : " + checkout);
			System.out.println("long Rent : " + days + " nights");
			System.out.println("Room Type : " + roomtype);
			System.out.println("Facility  : " + facility);
			System.out.println("Rent Fee  : Rp." + rentfee);
		}

		String valid;
		do {
			System.out.print("Are you sure want to input this data? [Y/N]: ");
			valid = scan.nextLine();
			if (valid.equals("Y")) {
				if (type == 1) {
					vguest.add(new PermanentGuest(name, birth, id, facility, checkin, checkout, roomtype, rentfee,
							yearly));
				} else if (type == 2) {
					vguest.add(new RegularGuest(name, birth, id, facility, checkin, checkout, roomtype));
				}
				totalroom += 1;
			} else if (valid.equals("N")) {
				return;
			}
		} while (valid.equals("Y") && valid.equals("N"));
	}

	public static long daysBetween(Date one, Date two) {
		long diff = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(diff);
	}
}

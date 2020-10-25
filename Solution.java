package com.task.arr.sort.search;

class User {
	private String name;
	private int bookingDateStart;
	private int bookingDateEnd;
	private String bookedVenue;

	public User(String name, int bookingDateStart, int bookingDateEnd, String bookedVenue) {
		super();
		this.name = name;
		this.bookingDateStart = bookingDateStart;
		this.bookingDateEnd = bookingDateEnd;
		this.bookedVenue = bookedVenue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBookingDateStart() {
		return bookingDateStart;
	}

	public void setBookingDateStart(int bookingDateStart) {
		this.bookingDateStart = bookingDateStart;
	}

	public int getBookingDateEnd() {
		return bookingDateEnd;
	}

	public void setBookingDateEnd(int bookingDateEnd) {
		this.bookingDateEnd = bookingDateEnd;
	}

	public String getBookedVenue() {
		return bookedVenue;
	}

	public void setBookedVenue(String bookedVenue) {
		this.bookedVenue = bookedVenue;
	}

}

public class Solution {

	public static void main(String[] args) {
		//-----------input start-------------------//
		User userListBooked[] = new User[3];
		User userListToBeBooked[] = new User[3];

		userListBooked[0] = new User("e", 7, 8, "futsal court");
		userListBooked[1] = new User("b", 3, 4, "badminton court");
		userListBooked[2] = new User("a", 1, 2, "futsal court");

		userListToBeBooked[0] = new User("c", 3, 4, "futsal court");
		userListToBeBooked[1] = new User("d", 1, 2, "badminton court");
		userListToBeBooked[2] = new User("f", 6, 7, "futsal court");
		//-----------input end--------------------//
		checkAvailability(userListBooked, userListToBeBooked);

	}

	public static void checkAvailability(User[] userListBooked, User[] userListToBeBooked) {
		int futsalCourtULB = sortVenue(userListBooked);
		int futsalCourtULTBB = sortVenue(userListToBeBooked);

		mergeIt(userListBooked);
		mergeIt(userListToBeBooked);

		int i = 0, j = 0;
		int m = userListBooked.length;
		int n = userListToBeBooked.length;
		int k = 0, l = 0;
//		boolean check = true;

		User[] possibleBooking = new User[n];
		User[] bookingNotPossible = new User[m];

		int x = 0, y = 0;
		for (int q = 0; q <n- futsalCourtULTBB; q++) {
			if (checkVenue(userListToBeBooked[q], userListBooked, 0, n-futsalCourtULB - 1)) {
				possibleBooking[x++] = userListToBeBooked[q];
			} else {
				bookingNotPossible[y++] = userListToBeBooked[q];
			}
		}

		for (int q = n-futsalCourtULTBB; q < n; q++) {
			if(checkVenue(userListToBeBooked[q], userListBooked, n-futsalCourtULB, n-1)) {
				possibleBooking[x++]=userListToBeBooked[q];
			}
			else {
				bookingNotPossible[y++]=userListToBeBooked[q];
			}
		}

		System.out.println("Possible booking list");
		for (int q = 0; q < m; q++) {
			if (possibleBooking[q] != null) {
				System.out.println(possibleBooking[q].getName());
			}
		}
		System.out.println("Not Possible booking list");
		for (int q = 0; q < n; q++) {
			if (bookingNotPossible[q] != null) {
				System.out.println(bookingNotPossible[q].getName());
			}
		}
	}

	public static boolean checkVenue(User checkV, User[] list, int start, int end) {

		int low = start;
		int high = end;
	
		
		
		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (low == high) {
				if (checkV.getBookingDateStart() > list[low].getBookingDateEnd()
						|| checkV.getBookingDateEnd() < list[low].getBookingDateStart()) {
					return true;
				}
			}

			if (mid == list.length - 1) {
				if (checkV.getBookingDateStart() > list[mid].getBookingDateEnd()) {
					return true;
				}
				if (mid - 1 >= 0) {
					if (checkV.getBookingDateEnd() < list[mid].getBookingDateStart()
							&& checkV.getBookingDateStart() > list[mid - 1].getBookingDateEnd()) {
						return true;
					}
				}
			}
			if (mid == 0) {
				if (checkV.getBookingDateEnd() < list[mid].getBookingDateStart()) {
					return true;
				}
				if (mid + 1 < list.length) {
					if (checkV.getBookingDateStart() > list[mid].getBookingDateEnd()
							&& checkV.getBookingDateEnd() < list[mid + 1].getBookingDateStart()) {
						return true;
					}
				}
			}

			if (checkV.getBookingDateStart() > list[mid].getBookingDateEnd()) {
				low = mid + 1;
			}

			else if (checkV.getBookingDateEnd() < list[mid].getBookingDateStart()) {
				high = mid - 1;
			} else
				return false;

		}

		return false;
	}

	public static int sortVenue(User[] userList) {
		int countFutsalCourt = 0;
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].getBookedVenue().equals("futsal court")) {
				countFutsalCourt++;
			}
		}
		User[] checkArr = new User[userList.length];

		for (int k = 0; k < userList.length; k++) {
			checkArr[k] = userList[k];
		}

		int i = 0, j = 0;
		for (int k = 0; k < userList.length; k++) {
			if (checkArr[k].getBookedVenue().equals("futsal court")) {
				userList[userList.length - countFutsalCourt + j] = checkArr[k];
				j++;
			} else {
				userList[i] = checkArr[k];
				i++;
			}
		}

		for (int k = 0; k < userList.length; k++) {
			System.out.println(userList[k].getBookedVenue());
		}
		return countFutsalCourt;

	}

	public static void mergeIt(User[] sortList) {
		int n = sortList.length;

		int countFutsalCourt = 0;
		for (int i = 0; i < sortList.length; i++) {
			if (sortList[i].getBookedVenue().equals("futsal court")) {
				countFutsalCourt++;
			}
		}

		merge(0,n- countFutsalCourt - 1, sortList);
		merge(n-countFutsalCourt, n - 1, sortList);
	}

	public static void merge(int l, int h, User[] sortList) {
		if (l < h) {
			int mid = l + (h - l) / 2;
			merge(l, mid, sortList);
			merge(mid + 1, h, sortList);
			sort(l, mid, h, sortList);
		}
	}

	public static void sort(int l, int m, int h, User[] sortList) {
		int n1 = m - l + 1;
		int n2 = h - m;

		User[] arr1 = new User[n1];
		User[] arr2 = new User[n2];

		for (int i = 0; i < n1; i++) {
			arr1[i] = sortList[l + i];
		}

		for (int i = 0; i < n2; i++) {
			arr2[i] = sortList[i + m + 1];
		}

		int i = 0, j = 0, k = l;

		while (i < n1 && j < n2) {
			if (arr1[i].getBookingDateStart() <= arr2[j].getBookingDateStart()) {
				sortList[k++] = arr1[i++];
			} else {
				sortList[k++] = arr2[j++];
			}
		}

		while (i < n1) {
			sortList[k++] = arr1[i++];
		}

		while (j < n2) {
			sortList[k++] = arr2[j++];
		}

	}
}

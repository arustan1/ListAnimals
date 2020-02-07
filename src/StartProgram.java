import java.util.List;
import java.util.Scanner;

import controller.ListAnimalHelper;
import model.ListAnimals;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListAnimalHelper lih = new ListAnimalHelper();

		private static void addAnAnimal() {
			// TODO Auto-generated method stub
			System.out.print("Enter a pet name: ");
			String name = in.nextLine();
			System.out.print("Enter pet type: ");
			String type = in.nextLine();
			System.out.print("Enter owner name: ");
			String owner = in.nextLine();
			
			ListAnimals toAdd = new ListAnimals(name,type,owner);
			lih.insertAnimal(toAdd);

		}

		private static void deleteAnAnimal() {
			// TODO Auto-generated method stub
			System.out.print("Enter the pet name to delete: ");
			String name = in.nextLine();
			System.out.print("Enter the pet type to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the owner name to delete: ");
			String owner = in.nextLine();
			
			ListAnimals toDelete = new ListAnimals(name,type,owner);
			lih.deleteAnimal(toDelete);

		}

		private static void editAnAnimal() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Pet");
			System.out.println("2 : Search by Owner");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListAnimals> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the pet name: ");
				String petName = in.nextLine();
				foundItems = lih.searchForItemByName(petName);
			} else {
				System.out.print("Enter the owner: ");
				String ownerName = in.nextLine();
				foundItems = lih.searchForItemByOwner(ownerName);

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListAnimals l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListAnimals toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName() + " from " + toEdit.getOwner());
				System.out.println("1 : Update Pet");
				System.out.println("2 : Update Owner");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New pet name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New Owner: ");
					String newOwner = in.nextLine();
					toEdit.setOwner(newOwner);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our pet directory! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a pet");
				System.out.println("*  2 -- Edit a pet");
				System.out.println("*  3 -- Delete a pet");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnAnimal();
				} else if (selection == 2) {
					editAnAnimal();
				} else if (selection == 3) {
					deleteAnAnimal();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListAnimals>allItems = lih.showAllItems();
			for(ListAnimals singleItem : allItems) {
				System.out.println(singleItem.returnItemDetails());
			}
			

		}

	}
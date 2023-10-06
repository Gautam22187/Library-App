import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("Library Portal Initialized.");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Librarian Menu");
                    System.out.println("1. Add Book");
                    System.out.println("2. Remove Book");
                    System.out.println("3. Register Member");
                    System.out.println("4. Remove Member");
                    System.out.println("5. View All Members and Fines");
                    System.out.println("6. View All Books");
                    System.out.println("7. Back");
                    System.out.print("Enter your choice: ");
                    int libChoice = sc.nextInt();
                    switch (libChoice) {
                        case 1:
                            System.out.print("Enter Book ID: ");
                            int bookId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Title: ");
                            String title = sc.nextLine();
                            System.out.print("Enter Author: ");
                            String author = sc.nextLine();
                            System.out.print("Enter Total Copies: ");
                            int totalCopies = sc.nextInt();
                            Book newBook = new Book(bookId, title, author, totalCopies);
                            library.addBook(newBook);
                            System.out.println("Book added successfully.");
                            break;

                        case 2:
                            System.out.print("Enter Book ID to remove: ");
                            int bookIdToRemove = sc.nextInt();
                            library.removeBook(bookIdToRemove);
                            System.out.println("Book removed successfully.");
                            break;

                        case 3:
                            System.out.print("Enter Member ID: ");
                            int memberId = sc.nextInt();
                            System.out.print("Enter Name: ");
                            String name = sc.next();
                            System.out.print("Enter Age: ");
                            int age = sc.nextInt();
                            System.out.print("Enter Phone Number: ");
                            String phoneNumber = sc.next();
                            Member newMember = new Member(memberId, name, age, phoneNumber);
                            library.registerMember(newMember);
                            System.out.println("Member registered successfully.");
                            break;

                        case 4:
                            System.out.print("Enter Member ID to remove: ");
                            int memberIdToRemove = sc.nextInt();
                            library.removeMember(memberIdToRemove);
                            System.out.println("Member removed successfully.");
                            break;

                        case 5:
                            library.viewAllMembersWithFines();
                            break;

                        case 6:
                            library.viewAllBooks();
                            break;

                        case 7:
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your Name: ");
                    String memberName = sc.nextLine();
                    System.out.print("Enter your Phone Number: ");
                    String phoneNumber = sc.nextLine();
                    Member loggedInMember = library.login(memberName, phoneNumber);

                    if (loggedInMember != null) {
                        System.out.println("Name: " + loggedInMember.getName());
                        System.out.println("Phone No: " + loggedInMember.getPhoneNumber());
                        System.out.println("Welcome " + loggedInMember.getName() + ". Member ID: " + loggedInMember.getMemberId());
                        int memberChoice;
                        do {
                            System.out.println("Member Menu");
                            System.out.println("1. List Available Books");
                            System.out.println("2. List My Books");
                            System.out.println("3. Issue Book");
                            System.out.println("4. Return Book");
                            System.out.println("5. Pay Fine");
                            System.out.println("6. Back");
                            System.out.print("Enter your choice: ");
                            memberChoice = sc.nextInt();
                            sc.nextLine();

                            switch (memberChoice) {
                                case 1:
                                    List<Book> availableBooks = library.getAvailableBooks();
                                    System.out.println("Available Books:");
                                    for (Book book : availableBooks) {
                                        System.out.println(book);
                                    }
                                    break;

                                case 2:
                                    List<Book> memberBooks = loggedInMember.getBorrowedBooks();
                                    System.out.println("Your Books:");
                                    for (Book book : memberBooks) {
                                        System.out.println(book);
                                    }
                                    break;

                                case 3:
                                    System.out.print("Enter Book ID to issue: ");
                                    int bookID = sc.nextInt();
                                    library.issueBook(loggedInMember, bookID);
                                    break;

                                case 4:
                                    System.out.print("Enter Book ID to return: ");
                                    int returnBookID = sc.nextInt();
                                    library.returnBook(loggedInMember, returnBookID);
                                    break;

                                case 5:
                                    System.out.print("Enter Fine Amount: ");
                                    int fineAmount = sc.nextInt();
                                    loggedInMember.finePay(fineAmount);
                                    break;

                                case 6:
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please select a valid option.");
                            }
                        } while (memberChoice != 6);
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 3:
                    System.out.println("Thanks for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);

        sc.close();
    }
}
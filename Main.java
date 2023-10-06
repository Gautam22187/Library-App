import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

class Book {
    private int bookId;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(int bookId, String title, String author, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("All copies of this book are currently borrowed.");
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        } else {
            System.out.println("All copies of this book are already returned.");
        }
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nTotal Copies: " + totalCopies +
                "\nAvailable Copies: " + availableCopies;
    }
}

class Member {
    private int memberId;
    private String name;
    private int age;
    private String phoneNumber;
    private List<Book> borrowedBooks;
    private int balance;
    private LocalTime b1_borrow_time;
    private LocalTime b1_return_time;
    private LocalTime b2_borrow_time;
    private LocalTime b2_return_time;
    private String Book1;
    private String Book2;

    public Member(int memberId, String name, int age, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new ArrayList<>();
        this.balance = 0;
    }

    public void borrowBook(Book book, int bookNumber) {
        if (bookNumber == 1) {
            if (borrowedBooks.size() < 2) {
                borrowedBooks.add(book);
                Book1 = book.getTitle();
                b1_borrow_time = LocalTime.now();
            } else {
                System.out.println("You can't borrow more than 2 books.");
            }
        } else if (bookNumber == 2) {
            if (borrowedBooks.size() < 2) {
                borrowedBooks.add(book);
                Book2 = book.getTitle();
                b2_borrow_time = LocalTime.now();
            } else {
                System.out.println("You can't borrow more than 2 books.");
            }
        }
    }

    public LocalTime getB1_borrow_time() {
        return b1_borrow_time;
    }

    public LocalTime getB2_borrow_time() {
        return b2_borrow_time;
    }

    public String getBook1() {
        return Book1;
    }

    public String getBook2() {
        return Book2;
    }

    public void returnBorrowedBook(Book book, int bookNumber) {
        borrowedBooks.remove(book);
        if (bookNumber == 1) {
            b1_return_time = LocalTime.now();
        } else if (bookNumber == 2) {
            b2_return_time = LocalTime.now();
        }
    }

    public LocalTime getBorrowTime(int bookNumber) {
        if (bookNumber == 1) {
            return b1_borrow_time;
        } else if (bookNumber == 2) {
            return b2_borrow_time;
        } else {
            return null; // Handle invalid bookNumber
        }
    }

    public LocalTime getReturnTime(int bookNumber) {
        if (bookNumber == 1) {
            return b1_return_time;
        } else if (bookNumber == 2) {
            return b2_return_time;
        } else {
            return null; // Handle invalid bookNumber
        }
    }


    public void finePay(int fineAmount) {
        this.balance += fineAmount;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.getBookId() == bookId);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void removeMember(int memberId) {
        members.removeIf(member -> member.getMemberId() == memberId);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAvailableCopies() > 0) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void issueBook(Member member, int bookId) {
        Book bookToIssue = findBookById(bookId);
        if (bookToIssue != null && member != null) {
            if (member.getBalance() == 0 && member.getBorrowedBooks().size() < 2 && bookToIssue.getAvailableCopies() > 0) {
                member.borrowBook(bookToIssue, 1);
                bookToIssue.borrowCopy();
            } else {
                System.out.println("Book issuance not allowed.");
            }
        } else {
            System.out.println("Book or member not found.");
        }
    }

    public void returnBook(Member member, int bookId) {
        Book bookToReturn = findBookById(bookId);
        if (bookToReturn != null && member != null) {
            member.returnBorrowedBook(bookToReturn, 1);
            calculateFine(member, bookId, 1);
        } else {
            System.out.println("Book or member not found.");
        }
    }

    private void calculateFine(Member member, int bookId, int bookNumber) {
        Book book = findBookById(bookId);
        if (book != null && member != null) {
            LocalTime borrowTime;
            LocalTime returnTime;

            if (bookNumber == 1 || bookNumber == 2) {
                borrowTime = member.getBorrowTime(bookNumber);
                returnTime = member.getReturnTime(bookNumber);
            } else {
                System.out.println("Invalid book number.");
                return;
            }

            if (borrowTime != null && returnTime != null) {
                Duration duration = Duration.between(borrowTime, returnTime);
                long seconds = duration.getSeconds();
                int daysLate = (int) (seconds / 86400); // 1 day = 86400 seconds
                int fineAmount = Math.max(0, daysLate - 10) * 3; // 3 rupees per day

                if (fineAmount > 0) {
                    System.out.println("Fine Amount: " + fineAmount + " rupees");
                    member.finePay(fineAmount);
                } else {
                    System.out.println("Book returned on time. No fine.");
                }
            } else {
                System.out.println("Invalid time information for the book.");
            }
        } else {
            System.out.println("Book or member not found.");
        }
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }


    public Member login(String memberName, String phoneNumber) {
        for (Member member : members) {
            if (member.getName().equals(memberName) && member.getPhoneNumber().equals(phoneNumber)) {
                return member;
            }
        }
        return null;
    }

    public void viewAllMembersWithFines() {
        System.out.println("All Members and Their Books:");
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Books Borrowed:");
            List<Book> memberBooks = member.getBorrowedBooks();
            if (memberBooks.isEmpty()) {
                System.out.println("No books borrowed.");
            } else {
                for (Book book : memberBooks) {
                    System.out.println("Book Title: " + book.getTitle());
                }
            }
            System.out.println("Fines to be Paid: Rs." + member.getBalance());
        }
    }

    public void viewAllBooks() {
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
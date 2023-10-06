# Library-App
Develop a Library Management System application using Object-Oriented Programming (OOP) principles.
**Classes:
Book Class:**

Represents a book with attributes like bookId, title, author, total copies, and available copies.
Provides methods to borrow and return copies, along with getter methods to access book information.
**Member Class:**

Represents a library member with attributes like memberId, name, age, phone number, borrowed books, balance (for fines), and borrowing and returning timestamps for two books.
Provides methods to borrow and return books, pay fines, and access member information.
**Library Class:**

Manages books and members in the library.
Provides methods to add/remove books, register/remove members, view available books, issue/return books, calculate fines, and more.
Implements a login method for members to log in based on their name and phone number.

**Main Class:**

App Class (Main):
Contains the main logic for the library management system.
It provides a menu-driven interface for both librarians and members.
Librarians can add/remove books, register/remove members, and view member information.
Members can log in, list available books, list their borrowed books, issue/return books, pay fines, and log out.
How to Use the Code:
Run the App class as your main program.
You will be presented with a menu to choose whether you want to enter as a librarian or a member.
If you select to enter as a librarian, you can perform actions related to books and members.
If you select to enter as a member, you'll be prompted to enter your name and phone number to log in.
After logging in as a member, you can perform actions related to borrowing, returning, and managing fines.
Here's a breakdown of some key functionalities:

**Librarian:**

Can add and remove books from the library.
Can register and remove members from the library.
Can view a list of all members along with their borrowed books and fines.
Can view a list of all available books in the library.

**Member:**

Members log in using their name and phone number.
Can view a list of available books.
Can view a list of their borrowed books.
Can issue and return books.
Can pay fines if they have any.

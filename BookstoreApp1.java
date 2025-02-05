import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;
    private int quantity;

    Scanner scanner = new Scanner(System.in);

    // Method to add new book
    public void addBook() {
        System.out.print("Enter title: ");
        title = scanner.nextLine();
        System.out.print("Enter author: ");
        author = scanner.nextLine();
        System.out.print("Enter price: ");
        price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        quantity = scanner.nextInt();
    }

    // Method to display book details
    public void displayBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
    }

    // Method to purchase books
    public double purchaseBook(int qty) {
        if (quantity >= qty) {
            quantity -= qty;
            return price * qty;
        } else {
            System.out.println("Sorry, requested quantity not available.");
            return 0;
        }
    }

    // Method to search book by title
    public boolean searchBook(String bookTitle) {
        if (title.equalsIgnoreCase(bookTitle)) {
            displayBook();
            return true;
        }
        return false;
    }
}

class Order {
    private List<Book> books;
    private double totalCost;

    public Order() {
        books = new ArrayList<>();
        totalCost = 0;
    }

    public void addBook(Book book, int qty) {
        books.add(book);
        totalCost += book.purchaseBook(qty);
    }

    public void displayOrder() {
        System.out.println("Books in order:");
        for (Book book : books) {
            book.displayBook();
        }
        System.out.println("Total Cost: $" + totalCost);
    }
}

public class BookstoreApp1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many books do you want to add? ");
        int numBooks = scanner.nextInt();

        Book[] books = new Book[numBooks];

        // Add books
        for (int i = 0; i < numBooks; i++) {
            books[i] = new Book();
            System.out.println("\nEnter details for book " + (i + 1) + ":");
            books[i].addBook();
        }

        Order order = new Order();

        int choice;
        do {
            System.out.println("\n*** Bookstore Menu ***");
            System.out.println("1. Display all books");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Purchase a book");
            System.out.println("4. View order history");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n*** All Books ***");
                    for (Book book : books) {
                        book.displayBook();
                    }
                    break;
                case 2:
                    System.out.print("Enter the title of the book you want to search: ");
                    scanner.nextLine(); // Consume newline
                    String searchTitle = scanner.nextLine();
                    boolean found = false;
                    for (Book book : books) {
                        found = book.searchBook(searchTitle);
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book you want to purchase: ");
                    scanner.nextLine(); // Consume newline
                    String purchaseTitle = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    int qty = scanner.nextInt();
                    found = false;
                    for (Book book : books) {
                        found = book.searchBook(purchaseTitle);
                        if (found) {
                            order.addBook(book, qty);
                            System.out.println("Book added to order.");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("\n*** Order History ***");
                    order.displayOrder();
                    break;
                case 5:
                    System.out.println("Thank you for visiting the bookstore.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }
}

import java.util.ArrayList;
import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

public class ShoppingCartSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();

    static void loadProducts() {
        products.add(new Product(101, "Laptop", 55000));
        products.add(new Product(102, "Headphones", 2000));
        products.add(new Product(103, "Mouse", 800));
        products.add(new Product(104, "Keyboard", 1500));
        products.add(new Product(105, "USB Cable", 500));
    }

    static void displayProducts() {
        System.out.println("\n===== AVAILABLE PRODUCTS =====");

        for (Product p : products) {
            System.out.printf("%d - %-15s Rs. %.2f%n",
                    p.id, p.name, p.price);
        }
    }

    static Product findProduct(int id) {
        for (Product p : products) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    static void addProduct() {
        displayProducts();

        System.out.print("\nEnter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Invalid Product ID!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        for (CartItem item : cart) {
            if (item.product.id == id) {
                item.quantity += quantity;
                System.out.println("Product quantity updated!");
                return;
            }
        }

        cart.add(new CartItem(product, quantity));
        System.out.println("Product added to cart!");
    }

    static void removeProduct() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        displayCart();

        System.out.print("\nEnter Product ID to remove: ");
        int id = sc.nextInt();

        for (CartItem item : cart) {
            if (item.product.id == id) {
                cart.remove(item);
                System.out.println("Product removed successfully!");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    static void updateQuantity() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        displayCart();

        System.out.print("\nEnter Product ID: ");
        int id = sc.nextInt();

        for (CartItem item : cart) {
            if (item.product.id == id) {
                System.out.print("Enter new quantity: ");
                int quantity = sc.nextInt();

                if (quantity <= 0) {
                    System.out.println("Invalid quantity.");
                } else {
                    item.quantity = quantity;
                    System.out.println("Quantity updated!");
                }
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    static void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n========== YOUR CART ==========");

        double subtotal = 0;

        for (CartItem item : cart) {
            double total = item.getTotal();

            System.out.printf("%-15s x%d  Rs. %.2f%n",
                    item.product.name,
                    item.quantity,
                    total);

            subtotal += total;
        }

        System.out.println("-------------------------------");
        System.out.printf("Subtotal:              Rs. %.2f%n", subtotal);
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        double subtotal = 0;

        for (CartItem item : cart) {
            subtotal += item.getTotal();
        }

        double discount = 0;

        if (subtotal >= 50000) {
            discount = subtotal * 0.10;
        } else if (subtotal >= 10000) {
            discount = subtotal * 0.05;
        }

        double finalAmount = subtotal - discount;

        System.out.println("\n========== BILL ==========");

        for (CartItem item : cart) {
            System.out.printf("%-15s x%d  Rs. %.2f%n",
                    item.product.name,
                    item.quantity,
                    item.getTotal());
        }

        System.out.println("--------------------------");
        System.out.printf("Subtotal:      Rs. %.2f%n", subtotal);
        System.out.printf("Discount:      Rs. %.2f%n", discount);
        System.out.printf("Final Amount:  Rs. %.2f%n", finalAmount);
        System.out.println("==========================");
        System.out.println("Thank you for shopping!");

        cart.clear();
    }

    public static void main(String[] args) {

        loadProducts();

        int choice;

        do {
            System.out.println("\n===== ONLINE SHOPPING CART =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Update Quantity");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;

                case 2:
                    addProduct();
                    break;

                case 3:
                    removeProduct();
                    break;

                case 4:
                    updateQuantity();
                    break;

                case 5:
                    displayCart();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}
package run;

import bussiness.util.InputMethods;

public class ContactManager {
    public static void showContact() {

        do {
            System.out.println();
            System.out.println();
            System.out.println("Contact Us");
            System.out.println();
            System.out.println("    Welcome to [Showroom THS], your premier destination for high-quality automobiles.\n" +
                    "We are dedicated to providing you with exceptional customer service and a wide selection of \n" +
                    "top-tier vehicles. Your feedback and inquiries are important to us. Please don't hesitate\n" +
                    "to get in touch with our team. We are here to assist you.\n");
            System.out.println();
            System.out.println("Contact Information");
            System.out.println();
            System.out.print("・Address: ");
            System.out.println("Tan Ky Tan Quy, Binh Tan");
            System.out.print("・Phone: ");
            System.out.println("0908250198");
            System.out.print("・Email: ");
            System.out.println("devst2025@gmail.com");
            System.out.println();
            System.out.println("Operating Hours");
            System.out.println();
            System.out.print("・Monday to Friday: ");
            System.out.println("9:00 AM - 6:00 PM");
            System.out.print("・Saturday: ");
            System.out.println("10:00 AM - 4:00 PM");
            System.out.print("・Sunday: ");
            System.out.println("Closed");
            System.out.println();
            System.out.println("Visit Our Showroom");
            System.out.println();
            System.out.println("    We invite you to visit our showroom to explore our collection of luxury cars \n" +
                    "and speak with our knowledgeable staff. We look forward to welcoming you to [Showroom THS] \n" +
                    "and helping you find your dream vehicle.\n");

            System.out.println("1. Back");
            System.out.println("2. Quit");
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("\u001B[36m----------End----------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 1) {
                break;
            }
        } while (true);
    }
}

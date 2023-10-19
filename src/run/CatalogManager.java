package run;

import bussiness.entity.Catalog;
import bussiness.entity.User;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CatalogManager {
    public static void showCatalogManager() {
        do {
            Menu.catalogManager();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showListCatalog();
                    break;
                case 2:
                    addNewCatalog();
                    break;
                case 3:
                    findCatalogByName();
                    break;
                case 4:
                    updateCatalog();
                    break;
                case 5:
                    hiddenCatalog();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 6) {
                break;
            }
        } while (true);
    }

    //Show List Account
    public static void showListCatalog() {
        List<Catalog> listCatalogs = CarDealer.catalogService.findAll();
        System.out.println("----------Brand list----------");
        for (Catalog catalog : listCatalogs) {
            System.out.printf("ID: %d - Brand's name: %s\n" +
                            "Descriptions: %s\n" +
                            "Status: %s\n",
                    catalog.getCatalogId(), catalog.getCatalogName(), catalog.getDesc(), (catalog.isStatus() ? "Active" : "Hidden"));
            System.out.println("----------------------------------------");
        }
    }

    //Add new catalog
    public static void addNewCatalog() {
        Catalog catalog = new Catalog();
        //ID
        catalog.setCatalogId(CarDealer.catalogService.getNewId());
        System.out.printf("Add a new brand with id: %d\n", catalog.getCatalogId());
        //Catalog Name
        System.out.print("\u001B[37mEnter brand's name: ");
        String catalogName = InputMethods.getString();
        catalog.setCatalogName(catalogName);
        //Descriptions
        System.out.print("\u001B[37mEnter descriptions: ");
        String desc = InputMethods.getString();
        catalog.setDesc(desc);
        //Status
        catalog.setStatus(true);
        CarDealer.catalogService.save(catalog);
        System.out.println("Brand added successfully.");
    }

    // Find User By Name
    public static void findCatalogByName() {
        System.out.print("Enter brand's name: ");
        String inputName = InputMethods.getString().trim();

        List<Catalog> list = CarDealer.catalogService.findCatalogByName(inputName);
        if (list.isEmpty()) {
            System.out.printf("No search results for the word '%s'.\n", inputName);
            return;
        }
        System.out.printf("----------Search result for '%s': ----------\n", inputName);
        for (Catalog catalog : list) {
            System.out.printf("ID: %d - Brand's name: %s\n" +
                            "Descriptions: %s - Status: %s\n",
                    catalog.getCatalogId(), catalog.getCatalogName(), catalog.getDesc(), (catalog.isStatus() ? "Active" : "Hidden"));
            System.out.println("----------------------------------------");
        }
    }

    //Update catalog
    public static void updateCatalog() {
        System.out.print("Enter brand's id: ");
        long inputId = InputMethods.getLong();
        Catalog catalog = CarDealer.catalogService.findById(inputId);
        if (catalog == null) {
            while (true) {
                System.out.printf("No brand with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    hiddenCatalog();
                } else {
                    return;
                }
            }
        }
        //Catalog Name
        System.out.print("\u001B[37mEnter brand's name: ");
        String catalogName = InputMethods.getString();
        catalog.setCatalogName(catalogName);
        //Descriptions
        System.out.print("\u001B[37mEnter descriptions: ");
        String desc = InputMethods.getString();
        catalog.setDesc(desc);
        //Status
        System.out.printf("Brand is %s, Do you want to change it?(Y/N)\n", (catalog.isStatus()?"actice": "hidden"));
        char key = InputMethods.getChar();
        if (key == 'Y') {
            catalog.setStatus(!catalog.isStatus());
        }
        CarDealer.catalogService.save(catalog);
        System.out.printf("Brand with id %d update successfully.\n", inputId);
    }

    // Change status user
    public static void hiddenCatalog() {
        System.out.print("Enter brand's id: ");
        long inputId = InputMethods.getLong();
        Catalog catalog = CarDealer.catalogService.findById(inputId);
        if (catalog == null) {
            while (true) {
                System.out.printf("No brand with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    hiddenCatalog();
                } else {
                    return;
                }
            }
        }
        if (!catalog.isStatus()) {
            System.out.println("This brand has been hidden.");
            return;
        }
        catalog.setStatus(false);
        System.out.printf("Brand with ID %d has been hidden.\n", inputId);
    }
}

package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AddFlowerCommand extends Command{
    private static Logger logger = Logger.getLogger(AddFlowerCommand.class.getName());
    private String flowerName;
    private Integer quantity;
    private String bouquetName;
    private boolean enableUi;

    public AddFlowerCommand(String flowerName, int quantity, String bouquetName, boolean enableUi) {
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
        this.enableUi = enableUi;
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        logger.entering(AddFlowerCommand.class.getName(), "execute");
        boolean doesBouquetExist = false;
        Bouquet bouquetToAddFlower = new Bouquet();
        for (int i = 0; !doesBouquetExist && i < bouquetList.size(); i++) {
            if (bouquetList.get(i).getBouquetName().equals(this.bouquetName)) {
                bouquetToAddFlower = bouquetList.get(i);
                doesBouquetExist = true;
            }
        }

        if (!doesBouquetExist) {
            throw new FlorizzException("No such bouquet is found.");
        }

        boolean doesFlowerExist = false;
        Flower flowerToBeAdded = new Flower();
        for (int i = 0; !doesFlowerExist && i < FlowerDictionary.size(); i++) {
            if (FlowerDictionary.get(i).getFlowerName().toLowerCase().equals(flowerName)) {
                //TODO should be extracted to its own function getFlower(String name, String colour)
                flowerToBeAdded = FlowerDictionary.get(i);
                doesFlowerExist = true;
            }
        }

        if (!doesFlowerExist) {
            throw new FlorizzException("Mentioned flower is not in our database." + System.lineSeparator() +
                                       "Check available flowers: `flower` " + System.lineSeparator() +
                                       "Add custom flowers: {{TO BE DONE}}");
        }

        bouquetToAddFlower.addFlower(flowerToBeAdded, this.quantity);
        if (enableUi) {
            ui.printAddFlowerSuccess(bouquetList, flowerName, quantity, bouquetName);
        }

        logger.exiting(AddFlowerCommand.class.getName(), "execute");
        return true;
    }
}

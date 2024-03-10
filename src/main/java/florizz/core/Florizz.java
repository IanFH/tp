package florizz.core;

import florizz.command.Command;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class Florizz {
    /**
     * Main entry-point for the java.florizz.core.Florizz application.
     */
    public static void main(String[] args) {
        ArrayList<Bouquet>  tempBouquetList = new ArrayList<>();
        boolean isRunning = true;
        Ui ui = new Ui();
        ui.printIntroMessage();

        while (isRunning){
            try {
                String input = ui.getInput();
                Command command = Parser.parse(input);
                isRunning = command.execute(tempBouquetList, ui);
            } catch(FlorizzException error){
                ui.printError(error);
            }
        }
    }
}

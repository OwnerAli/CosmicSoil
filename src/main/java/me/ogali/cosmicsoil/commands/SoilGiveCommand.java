package me.ogali.cosmicsoil.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.ogali.cosmicsoil.registries.SoilRegistry;
import me.ogali.cosmicsoil.soils.CustomSoil;
import me.ogali.cosmicsoil.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class SoilGiveCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();

        if (args.length < 2) {
            Chat.tell(sender, "&cUsage: /soilgive <player> <soil_id> [amount]");
            return;
        }

        // Get target player
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            Chat.tell(sender, "&cPlayer '" + args[0] + "' not found.");
            return;
        }

        // Get soil by ID
        String soilId = args[1];
        Optional<CustomSoil> soilOptional = SoilRegistry.getInstance().getSoilById(soilId);
        if (soilOptional.isEmpty()) {
            Chat.tell(sender, "&cSoil with ID '" + soilId + "' not found.");
            return;
        }

        // Get amount (default: 1)
        int amount = 1;
        if (args.length >= 3) {
            try {
                amount = Integer.parseInt(args[2]);
                if (amount <= 0) {
                    Chat.tell(sender, "&cAmount must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                Chat.tell(sender, "&cInvalid amount specified.");
                return;
            }
        }

        // Create and give soil item
        Optional<ItemStack> soilItemOptional = soilOptional.get().makeCustomItem();
        if (soilItemOptional.isEmpty()) {

            Chat.tell(sender, "&cFailed to create soil item.");
            return;
        }

        ItemStack soilItem = soilItemOptional.get();
        soilItem.setAmount(amount);
        targetPlayer.getInventory().addItem(soilItem);

        Chat.tell(sender, "&aGave " + amount + " " + soilId + " soil to " + targetPlayer.getName() + ".");
    }

}
package cz.sycha.hattie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by online players!");
            return true;
        }

        Player player = (Player) sender;

        PlayerInventory inv = player.getInventory();
        ItemStack itemToUse = inv.getItemInMainHand().clone();
        Material itemType = itemToUse.getType();
        itemToUse.setAmount(1);
        ItemStack oldItem = inv.getHelmet();

        inv.setHelmet(itemToUse);
        if (inv.getItemInMainHand().getAmount() > 1) {
        	inv.getItemInMainHand().setAmount(inv.getItemInMainHand().getAmount() - 1);
        }
        else {
        	inv.setItemInMainHand(null);
        }
        if (oldItem != null) inv.addItem(new ItemStack[] { oldItem });

        player.sendMessage(ChatColor.AQUA + itemType.name() + " has been put on your head!");

        return true;
    }
}

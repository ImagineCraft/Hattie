package cz.sycha.hattie;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Logger log = Logger.getLogger("minecraft");

    @Override
    public void onEnable() {
        log.info(ChatColor.AQUA + "[Hattie] - Enabling plugin");
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "[Hattie] - Disabling plugin");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by online players!");
            return true;
        }

        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("hat") && sender.hasPermission("hattie.use")) {
            PlayerInventory inv = player.getInventory();
            ItemStack itemToUse = inv.getItemInMainHand();
            ItemStack oldItem = inv.getHelmet();

            inv.setHelmet(itemToUse);
            inv.remove(itemToUse);
            inv.addItem(new ItemStack[] { oldItem });

            player.sendMessage(ChatColor.AQUA + "The item in your hand has been put on your head!");

            return true;
        }
        return false;
    }
}

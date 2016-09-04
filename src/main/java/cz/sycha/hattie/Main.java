package cz.sycha.hattie;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * A simple Bukkit plugin that allows you to put items on your head.
 *
 * @author JacobCZ <jakub@sycha.cz>
 * @version See plugin.yml
 */
public class Main extends JavaPlugin {
    private Logger log = Logger.getLogger("minecraft");

    private final String VERSION = this.getDescription().getVersion();

    @Override
    public void onEnable() {
        log.info(ChatColor.AQUA + "[Hattie] - Enabling plugin version - " + this.VERSION);
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "[Hattie] - Disabling plugin version - " + this.VERSION);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("hat") && sender.hasPermission("hattie.use")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be used by online players!");
                return true;
            }

            Player player = (Player) sender;

            try {
                PlayerInventory inv = player.getInventory();
                ItemStack itemToUse = inv.getItemInMainHand();
                ItemStack oldItem = inv.getHelmet();

                inv.setHelmet(itemToUse);
                inv.removeItem(new ItemStack[] {itemToUse});

                if(oldItem != null) {
                    inv.addItem(new ItemStack[] { oldItem });
                }

                player.sendMessage(ChatColor.AQUA + "The item in your hand has been put on your head!");
            } catch(CommandException e) {
                log.severe("CommandException: " + e.getMessage());
                e.printStackTrace();
            } catch (NullPointerException e) {
                log.severe("NullPointerException: " + e.getMessage());
                e.printStackTrace();
            }


            return true;
        }
        return false;
    }
}

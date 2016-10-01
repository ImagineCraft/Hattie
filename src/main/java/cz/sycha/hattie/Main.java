package cz.sycha.hattie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
	private Logger log = Logger.getLogger("minecraft");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

			Material itemType = itemToUse.getType();
			player.sendMessage(ChatColor.AQUA + itemType.name() + " has been put on your head!");
			if(oldItem != null) {
				inv.addItem(new ItemStack[] { oldItem });
			}
		} catch(CommandException e) {
			log.severe("CommandException: " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			log.severe("NullPointerException: " + e.getMessage());
			e.printStackTrace();
		}

		return true;
	}
}

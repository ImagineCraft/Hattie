package cz.sycha.hattie;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
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
			int itemAmount = itemToUse.getAmount();
			
			if( itemAmount > 1 ) 
			{ 
				itemToUse.setAmount( 1 );
			}
			inv.setHelmet(itemToUse);
			Material itemType = itemToUse.getType();
			// player.sendMessage(ChatColor.AQUA + itemType.name() + " has been put on your head!");
			// Apparently, in the latest version of spigot, AIR has a zero count.
			if( !itemType.equals( Material.AIR ) )
			{
				itemToUse.setAmount( itemAmount - 1 );
			}
			inv.setItemInMainHand( itemToUse );
			
			if(oldItem != null) {
				HashMap<Integer,ItemStack> resMap = inv.addItem(oldItem);
				if( !resMap.isEmpty() )
				{
					Location curLoc = player.getEyeLocation();
					World curWorld = curLoc.getWorld();
					curWorld.dropItem( curLoc, resMap.get( Integer.valueOf( 0 ) ) );
				}
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

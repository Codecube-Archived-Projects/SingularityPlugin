package sonarbeserk.github.io.inventoryserializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class InventorySerializer 
{
	public static byte[] serializeItemStacks(ItemStack[] inv)
	{
        try 
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BukkitObjectOutputStream bos = new BukkitObjectOutputStream(os);
            bos.writeObject(inv);
            bos.close();
            return os.toByteArray();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace ( );
            return null;
        }
    }

    public static ItemStack[] deserializeItemStacks(byte[] b)
    {
        try 
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            ItemStack[] tr = (ItemStack[]) bois.readObject();
            bois.close();
            return tr;
        } 
        catch (Exception ex) 
        {
        	ex.printStackTrace ( );
        	return null;
        }
    }
}

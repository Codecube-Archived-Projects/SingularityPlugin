package sonarbeserk.github.io.inventoryserializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class InventorySerializer 
{
	public static String serializeItemStacks(ItemStack[] inv)
	{
        try 
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BukkitObjectOutputStream bos = new BukkitObjectOutputStream(os);
            bos.writeObject(inv);
            bos.close();
            //The ISO-8859-1 encoding has characters for each possible byte. This allows for uncorrupted storage in string form.
            return new String ( os.toByteArray(), "ISO-8859-1" );
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace ( );
            return null;
        }
    }

    public static ItemStack[] deserializeItemStacks(String b)
    {
        try 
        {
        	//The ISO-8859-1 encoding has characters for each possible byte. This allows for uncorrupted transition from string form.
        	byte[] ba = b.getBytes ( "ISO-8859-1" );
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
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

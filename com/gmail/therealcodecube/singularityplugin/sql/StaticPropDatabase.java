package com.gmail.therealcodecube.singularityplugin.sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.gmail.therealcodecube.singularityplugin.props.Prop;

//This database is used for storing lists of props that can be added to a world.
public class StaticPropDatabase 
{
	private String tableName;
	private Vector < Prop > props = new Vector < Prop > ( );
	
	public StaticPropDatabase ( String tn )
	{
		tableName = tn;
		if ( !SQLInterface.isTable ( tableName ) )
		{
			String query = "CREATE TABLE " + tableName + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, serialized TEXT );";
			SQLInterface.sendQuery ( query );
		}
		else
		{
			loadFromDatabase ( );
		}
	}
	
	public StaticPropDatabase ( String tn, Vector < Prop > p )
	{
		this ( tn );
		props = p;
	}
	
	//Serialize the prop and add it to the database.
	public void addProp ( Prop p )
	{
		props.add ( p );
		//Full query: INSERT INTO "table" ( serialized ) VALUES ( "930n1[}\a3rnn????" );
		String query = "INSERT INTO \"" + tableName + "\" ( serialized ) VALUES ( \""; 
		query += serializeProp ( p );
		query += "\");";
		SQLInterface.sendQuery ( query );
	}

	//Loads the serialized props from the database into the props vector.
	private void loadFromDatabase ( )
	{
		props.clear ( );
		String query = "SELECT serialized FROM \"" + tableName + "\";";
		ResultSet r = SQLInterface.sendQuery ( query );
		while ( true )
		{
			try 
			{
				props.add ( deserializeProp ( r.getString ( 1 ) ) );
				if ( !r.next ( ) ) { return; }
			} 
			catch ( Exception e ) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Serializes a prop into lossless ISO-8859-1 encoded string format.
	private static String serializeProp ( Prop p )
	{
		//Derived from sonarbeserk's inventory serializer code.
		try 
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BukkitObjectOutputStream bos = new BukkitObjectOutputStream(os);
            bos.writeObject(p);
            bos.close();
            //The ISO-8859-1 encoding has characters for each possible byte. This allows for uncorrupted storage in string form.
            return new String ( os.toByteArray(), "ISO-8859-1" );
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace ( );
            return "";
        }
	}
	
	//Deserializes a prop from lossless ISO-8859-1 endcoded string format.
	private static Prop deserializeProp ( String s )
	{
		//Derived from sonarbeserk's inventory serializer code.
		try 
        {
        	//The ISO-8859-1 encoding has characters for each possible byte. This allows for uncorrupted transition from string form.
        	byte[] ba = s.getBytes ( "ISO-8859-1" );
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            Prop tr = (Prop) bois.readObject();
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

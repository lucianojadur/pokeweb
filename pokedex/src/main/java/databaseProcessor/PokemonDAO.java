package databaseProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import pokemon.Pokemon;

public class PokemonDAO {
	private static final String SELECT = "SELECT name, found_level, actual_level, types_num, types, abilities_num, abilities, ev_num, evs, previous_num, prev";

	public HashMap<String, Pokemon> getPokemonsFromDB(){
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		HashMap<String,Pokemon> pokemons = new HashMap<String,Pokemon>();
		
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement(SELECT);
			rs = stm.executeQuery();
			

			String name = rs.getString("name");
			Integer[] lvls = collectLevels(rs.getInt("found_level"), rs.getInt("actual_level"));
			Integer types_num = rs.getInt("types_num");
			// get vector of types_num types
			HashSet<String> abilities = new HashSet<String>(rs.getInt("abilities_num"));
			//get abilities
			//same for evs and previous
			
			while(rs.next()) {
				Pokemon p = new Pokemon(name, lvls, abilities);
				pokemons.putIfAbsent(p.name(), p);
			}
		}catch (SQLException e){
			e.printStackTrace(System.out);
		}finally {
			DBConnection.close(rs);
			DBConnection.close(stm);
			DBConnection.close(con);
		}
		return pokemons;
		
	}
	
	
	private Integer[] collectLevels(int lvFound, int lvMin){
		Integer[] lvs = new Integer[2];
		try{
			lvs[0] = lvFound;
			lvs[1] = lvMin;
		}catch(Exception e){
			lvs[0] = lvs[1] = 0;
		}
		return lvs;
	}

}

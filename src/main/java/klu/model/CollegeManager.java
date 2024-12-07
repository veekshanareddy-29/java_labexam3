package klu.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CollegeManager 
{
  JdbcTemplate jdbcTemplate;
  
  @Autowired 
  public CollegeManager(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }
  
  
  //INSERT OPERATION USING JDBC TEMPLATE
  public String saveData(College C)
  {
    try
    {
      String qry =" insert into college values('"+C.getName() +"', '"+ C.getAcronym() +"')";
      jdbcTemplate.update(qry);
      return "New College data has been added";
      
    }
    catch(Exception e)
    {
      return e.getMessage();
    }
  }
  
  //UPDATE OPERATION
  public String updateData(College C)
  {
	  try
	  {
		 String qry = "update college set name='"+ C.getName() +"' where acronym='"+C.getAcronym() +"'"; 
		 jdbcTemplate.update(qry);
		 return "Updated";
	  }catch(Exception e)
	  {
		  return e.getMessage();
	  }
  }
  
  //DELETE OPERATION
  public String deleteData(String acronym) {
	  try
	  {
		  String qry = "delete from college where acronym='"+acronym +"'";
		  jdbcTemplate.update(qry);
		  return "Deleted";
	  }catch(Exception e)
	  {
		  return e.getMessage();
	  }
  }
  
  //READ OPERATION
  public List<String> readData()
  {
	String qry = "select * from college";
	List<College> clist = jdbcTemplate.query(qry, new RowMapper<College>() {

		@Override
		public College mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			College tmp = new College();
			tmp.setName(rs.getString(1));
			tmp.setAcronym(rs.getString(2));
			return tmp;
		}});
	
	List<String> result = new ArrayList<String>();
	for(College C:clist) {
		result.add(toJSON(C));
	}
	return result;
  }
  
  //Converting Java object to JSON
  public String toJSON(Object obj) {
	  Gson G = new GsonBuilder().create();
	  return G.toJson(obj);
  }

}
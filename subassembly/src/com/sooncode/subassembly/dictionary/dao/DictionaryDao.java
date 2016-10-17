package com.sooncode.subassembly.dictionary.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.entity.Groups;

/**
 * 数据字典Dao
 * 
 * @author pc
 *
 */
public class DictionaryDao implements DictionaryDaoI {

	private MiniJdbc jdbc = new MiniJdbc("MYSQL", "127.0.0.1", "3306", "test", "UTF-8", "root", "hechenwe@gmail.com");

	private static final String GROUPS_TABLE_NAME = "GROUPS";

	private static final String DICTIONARY_TABLE_NAME = "DICTIONARY";

	/** 日期格式化 格式字符串 */
	private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public final static Logger logger = Logger.getLogger("DictionaryDao.class");

	
	@Override
	public Long addDictionaryGroups(Groups g) {
        
		if (isNotNullOrNotNul(g)==false||isNotNullOrNotNul(g.getGroupCode(), g.getGroupName(), g.getCreateDate(), g.getGroupState()) == false) {
			return null;
		}
		String createDate = new SimpleDateFormat(DATE_FORMAT).format(g.getCreateDate());
		 
		String sql = "INSERT INTO " + GROUPS_TABLE_NAME + " (GROUP_CODE,GROUP_NAME,GROUP_STATE,CREATE_DATE) VALUES ('" + g.getGroupCode() + "','" + g.getGroupName() + "','" + g.getGroupState() + "','" + createDate + "')";
		return jdbc.executeUpdate(sql);
	}

	
	
	@Override
	public Long updateDictionaryGroups(Groups g) {
		
		//if(g==null || isNotNullOrNotNul(g.))
		
		
		StringBuffer str = new StringBuffer();
		 
		 
		if(g==null ||g.getGroupId()==null || (g.getGroupCode()==null&&g.getGroupName()==null&&g.getGroupState()==null)){
		   return null;	
		}
		
		if(g.getGroupCode()!=null){
			str.append(" GROUP_CODE ='"+g.getGroupCode()+"' , "); 
		}
		
		if(g.getGroupName()!=null){
			str.append(" GROUP_NAME ='"+g.getGroupName()+"' , "); 
		}
		
		if(g.getGroupState() !=null){
			str.append(" GROUP_STATE ='"+g.getGroupState()+"' "); 
		}
		
		 
		
		String sql = "UPDATE "+GROUPS_TABLE_NAME+" SET "+str.toString()+" WHERE GROUP_ID = '"+g.getGroupId()+"'";
		
		return jdbc.executeUpdate(sql);
	}

	
	
	@Override
	public Long removeDictionaryGroups(String groupCode) {
		if(groupCode==null || groupCode.equals("")){
			return null;
		}
		
		String sql = "UPDATE "+GROUPS_TABLE_NAME+" SET GROUP_STATE='0' WHERE GROUP_CODE = '"+groupCode+"'";
		return jdbc.executeUpdate(sql);
	}

	@Override
	public Long addDictionary(Dictionary dictionary) {
		String sql = "";
		return jdbc.executeUpdate(sql);
	}

	
	
	@Override
	public Long updateDictionary(Dictionary dictionary) {
		String sql = "";
		return jdbc.executeUpdate(sql);
	}

	
	
	@Override
	public Long removeDictionary(Integer dictionaryId) {
		String sql = "";
		return jdbc.executeUpdate(sql);
	}

	
	
	@Override
	public List<Dictionary> getDictionaries(String groupCode) {
		String sql = "SELECT D.DICTIONARY_ID ,D.DICTIONARY_CODE,D.DICTIONARY_NAME ,D.DICTIONARY_STATE ,D.CREATE_DATE ,D.GROUP_ID FROM " + DICTIONARY_TABLE_NAME + " D , " + GROUPS_TABLE_NAME + " G  WHERE D.GROUP_ID = G.GROUP_ID AND G.GROUP_CODE ='" + groupCode + "'";
		List<Map<String, Object>> list = jdbc.executeQuery(sql);

		@SuppressWarnings("unchecked")
		List<Dictionary> dictionarys = (List<Dictionary>) jdbc.getObject(list, Dictionary.class);

		return dictionarys;
	}

	
	
	@Override
	public Dictionary getDictionary(String groupCode, String dictionaryCode) {
		 
		return null;
	}

	
	
	public static void main(String[] args) {

		// List<Dictionary> list = new
		// DictionaryDao().getDictionaries("userType");

		// for (Dictionary d : list) {

		// System.out.println(d.getDictionaryCode()+" -
		// "+d.getDictionaryName());
		// }
		Groups g = new Groups();
		g.setGroupId(235);
		g.setGroupCode("submitType");
		g.setGroupName("提交类型");
		g.setGroupState(1);
		g.setCreateDate(new Date());
		new DictionaryDao().removeDictionaryGroups("submitType");

	}

	/**
	 * 对象非空验证，字符串非空字符串验证。
	 * 
	 * @param objs
	 * @return 有一个空, 或者有一个空字符,返回false ,否则返回true.
	 */
	private static boolean isNotNullOrNotNul(Object... objs) {

		for (int i = 0; i < objs.length; i++) {
			if (objs[i] == null) {
				return false;
			} else {
				String className = objs[i].getClass().getName();
				if (className.equals("java.lang.String") && "".equals(objs[i].toString())) {
					return false;
				}
			}
		}
		return true;
	}

}

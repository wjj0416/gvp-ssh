package wg.gvp.dao;
import java.util.List;
import java.util.Set;

import wg.gvp.pojo.News;
public interface INewsDAO {
	boolean doCreate(News vo) throws Exception ;
	boolean doUpdate(News vo) throws Exception ;
	boolean doRemoveBatch(Set<Integer> ids ) throws Exception ;
	List<News> findAll() throws Exception ; 
	List<News> findAllSplit (String column,String keyword,Integer currentPage ,Integer lineSize) throws Exception;
	Integer getAllCount(String column,String keyWord) throws Exception ;
}

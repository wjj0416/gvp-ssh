package wg.gvp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import wg.gvp.pojo.News;

public interface INewsService {
	boolean insert(News vo ) throws Exception ;
	boolean update(News vo ) throws Exception ;
	boolean delete(Set<Integer> ids ) throws Exception ;
	List<News> list() throws Exception ;
	Map<String, Object> list(String column,String keyword,Integer currentPage,Integer lineSize) throws Exception ;
	
}

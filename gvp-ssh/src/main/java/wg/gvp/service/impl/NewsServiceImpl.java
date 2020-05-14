package wg.gvp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wg.gvp.dao.INewsDAO;
import wg.gvp.pojo.News;
import wg.gvp.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService{
	@Resource
	private INewsDAO newsDAO ;
	
	@Override
	public boolean insert(News vo) throws Exception {
		return this.newsDAO.doCreate(vo);
	}

	@Override
	public boolean update(News vo) throws Exception {
		return this.newsDAO.doUpdate(vo);
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		return this.newsDAO.doRemoveBatch(ids);
	}

	@Override
	public List<News> list() throws Exception {
		return this.newsDAO.findAll();
	}

	@Override
	public Map<String, Object> list(String column, String keyword, Integer currentPage, Integer lineSize)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("allNews", this.newsDAO.findAllSplit(column, keyword, currentPage, lineSize)) ;
		map.put("newsCount",this.newsDAO.getAllCount(column, keyword)) ;
 		return map;
	}
	
}

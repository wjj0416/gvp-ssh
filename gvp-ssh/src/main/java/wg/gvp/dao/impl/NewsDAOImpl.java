package wg.gvp.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import wg.gvp.dao.INewsDAO;
import wg.gvp.pojo.News;

@Component
public class NewsDAOImpl implements INewsDAO{
	@Resource
	private SessionFactory sessionFactory ;
	@Override
	public boolean doCreate(News vo) throws Exception {
		return sessionFactory.getCurrentSession().save(vo) != null ;
	}

	@Override
	public boolean doUpdate(News vo) throws Exception {
		String hql = "UPDATE News SET title=?1,pubdate=?2,content=?3 WHERE nid=?4" ;
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql) ;
		query.setParameter(1, vo.getTitle()) ;
		query.setParameter(2, vo.getPubdate()) ;
		query.setParameter(3, vo.getContent()) ;
		query.setParameter(4, vo.getNid()) ;
		return query.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		StringBuffer buf = new StringBuffer() ;
		buf.append("DELETE FROM News WHERE nid IN (") ;
		Iterator<Integer> iter = ids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.hasNext()).append(",") ;
		}
		buf.delete(buf.length()-1,buf.length()).append(")") ;
		Query query = this.sessionFactory.getCurrentSession().createQuery(buf.toString()) ;
		return query.executeUpdate()>0;
	}

	@Override
	public List<News> findAll() throws Exception {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(News.class);
		return criteria.list();
	}

	@Override
	public List<News> findAllSplit(String column, String keyword, Integer currentPage, Integer lineSize)
			throws Exception {
		String hql = "FROM News AS n WHERE n." + column + "LIKE ?1" ;
		Query<News> query = this.sessionFactory.getCurrentSession().createQuery(hql,News.class) ;
		query.setParameter(0, "%" + keyword + "%") ;
		query.setFetchSize((currentPage-1)*lineSize) ;
		query.setMaxResults(lineSize) ;
		return query.list();
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		String hql = "SELECT COUNT(*) News AS n WHERE n." + column + "LIKE ?1" ;
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql) ;
		query.setParameter(0, "%" + keyWord + "%") ;
		Long count = (Long)query.uniqueResult() ;
		return count.intValue() ;
	}
}

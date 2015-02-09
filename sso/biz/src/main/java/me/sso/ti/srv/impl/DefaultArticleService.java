package me.sso.ti.srv.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.sso.ti.dataobject.ArticleDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.ArticleSearchRequest;
import me.sso.ti.srv.ArticleService;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.PageQuery;
import me.sso.ti.vo.ArticleVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:37:37
 */
@Service(value = "articleService")
public class DefaultArticleService extends BaseService implements ArticleService {

	@Override
	public Result search(ArticleSearchRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM article a WHERE 1 = 1 ");
		Map<String, Object> args = new HashMap<String, Object>();
		Long aid = request.getId();
		if (aid != null && aid > 0L) {
			sb.append(" AND a.id = :aid ");
			args.put("aid", aid);
		}
		String title = request.getTitle();
		if (StringUtils.isNotBlank(title)) {
			sb.append(" AND a.title LIKE :title ");
			args.put("title", "%" + title + "%");
		}
		sb.append(" ORDER BY a.gmt_created DESC, a.status DESC ");
		sb.append(" LIMIT :start, :size ");
		Integer page = request.getPage();
		page = (page != null && page > 0) ? page : 1;
		PageQuery pageQuery = new PageQuery(page);
		args.put("start", pageQuery.getIndex());
		args.put("size", pageQuery.getSize());
		List<ArticleDO> articleDOList = articleDAO.queryNative(sb.toString(), args);
		if (CollectionUtils.isEmpty(articleDOList)) {
			return Result.newError().with(ResultCode.Error_Article_Empty);
		}
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		for (ArticleDO article : articleDOList) {
			ArticleVO vo = ArticleVO.newInstance(article, true);
			if(vo == null) {
				continue;
			}
			articleList.add(vo);
		}
		return Result.newSuccess().with(ResultCode.Success).with("articleList", articleList);
	}

	@Override
	public Result getArticle(Long id) {
		if(id == null || id <= 0L) {
			return Result.newError().with(ResultCode.Error_Article_NotExist);
		}
		ArticleDO article = articleDAO.get(id);
		if(article == null) {
			return Result.newError().with(ResultCode.Error_Article_NotExist);
		}
		return Result.newSuccess().with(ResultCode.Success).with("article", ArticleVO.newInstance(article, false));
	}
}
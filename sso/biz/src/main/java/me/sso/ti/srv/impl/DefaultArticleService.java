package me.sso.ti.srv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.sso.ti.dataobject.ArticleDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.ArticleDetailRequest;
import me.sso.ti.ro.ArticleSearchRequest;
import me.sso.ti.ro.CreateArticleRequest;
import me.sso.ti.srv.ArticleService;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.GzipService;
import me.sso.ti.srv.ImageService;
import me.sso.ti.srv.PageQuery;
import me.sso.ti.vo.ArticleVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:37:37
 */
@Service("articleService")
public class DefaultArticleService extends BaseService implements ArticleService {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private GzipService gzipService;
	
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result createArticle(CreateArticleRequest request) {
		ArticleDO article = new ArticleDO();
		article.setContent(request.getContent());
		article.setCatId(request.getCatId());
		Date date = new Date();
		article.setGmt_created(date);
		article.setGmt_modified(date);
		article.setTitle(request.getTitle());
		article.setStatus(ArticleDO.Published);
		Result uploadAvatar = imageService.upload(request.getAvatar());
		if(uploadAvatar.isSuccess()) {
			article.setAvatar((Long) uploadAvatar.get("image_id"));
		}
		Result uploadBackground = imageService.upload(request.getBackground());
		if(uploadBackground.isSuccess()) {
			article.setBackground((Long) uploadBackground.get("image_id"));
		}
		Result uploadGzip = gzipService.upload(request.getGzip());
		if(uploadGzip.isSuccess()) {
			article.setGzip((Long) uploadGzip.get("gzip_id"));
		}
		articleDAO.persist(article);
		return Result.newSuccess().with(ResultCode.Success).response(ArticleVO.newInstance(article, false));
	}

	@Override
	public Result search(ArticleSearchRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM article a WHERE status = " + ArticleDO.Published);
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
			request.getQuery().reset();
			return Result.newError().with(ResultCode.Error_Article_Empty);
		}
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		Long userId = -1L;
		if(StringUtils.isNotBlank(request.getOpen_id())) {
			Result doPrivileged = doPrivileged(request);
			if(doPrivileged.isSuccess()) {
				userId = doPrivileged.getResponse(Long.class);
			}
		}
		for (ArticleDO article : articleDOList) {
			ArticleVO vo = ArticleVO.newInstance(article, true);
			if(vo == null) {
				continue;
			}
			if(userId > 0L) {
				vo.setFavorite(isFavorite(userId, article.getId()));
			}
			articleList.add(vo);
		}
		return Result.newSuccess().with(ResultCode.Success).with("articleList", articleList);
	}

	@Override
	public Result getArticle(Long id, ArticleDetailRequest request) {
		if(id == null || id <= 0L) {
			return Result.newError().with(ResultCode.Error_Article_NotExist);
		}
		ArticleDO article = articleDAO.get(id);
		if(article == null) {
			return Result.newError().with(ResultCode.Error_Article_NotExist);
		}
		ArticleVO vo = ArticleVO.newInstance(article, false);
		if(request != null) {
			if(StringUtils.isNotBlank(request.getOpen_id())) {
				Result doPrivileged = doPrivileged(request);
				if(doPrivileged.isSuccess()) {
					Long userId = doPrivileged.getResponse(Long.class);
					vo.setFavorite(isFavorite(userId, article.getId()));
				}
			}
		}
		return Result.newSuccess().with(ResultCode.Success).with("article", vo);
	}
}
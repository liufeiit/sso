package me.sso.ti.srv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.sso.ti.dataobject.ArticleDO;
import me.sso.ti.dataobject.FavoriteDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.FavoriteRequest;
import me.sso.ti.ro.PageRequest;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.FavoriteService;
import me.sso.ti.srv.PageQuery;
import me.sso.ti.vo.ArticleVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:25:44
 */
@Service("favoriteService")
public class DefaultFavoriteService extends BaseService implements FavoriteService {

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result doFavorite(FavoriteRequest request) {
		Result privileged = doPrivileged(request);
		if(!privileged.isSuccess()) {
			return privileged;
		}
		Long userId = privileged.getResponse(Long.class);
		if (isFavorite(userId, request.getArticle_id())) {
			return Result.newSuccess().with(ResultCode.Error_Fav_Article);
		}
		FavoriteDO favorite = new FavoriteDO();
		favorite.setArticle_id(request.getArticle_id());
		favorite.setGmt_created(new Date());
		favorite.setUser_id(userId);
		favoriteDAO.persist(favorite);
		return Result.newSuccess().with(ResultCode.Success);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result doCancel(FavoriteRequest request) {
		Result privileged = doPrivileged(request);
		if(!privileged.isSuccess()) {
			return privileged;
		}
		Long userId = privileged.getResponse(Long.class);
		Object[] args = new Object[] { userId, request.getArticle_id() };
		String query = "DELETE FROM favorite WHERE user_id = ? AND article_id = ?";
		favoriteDAO.queryNativeUpdate(query, args);
		return Result.newSuccess().with(ResultCode.Success);
	}

	@Override
	public Result favList(PageRequest request) {
		Result privileged = doPrivileged(request);
		if(!privileged.isSuccess()) {
			return privileged;
		}
		Long userId = privileged.getResponse(Long.class);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("user_id", userId);
		Integer page = request.getPage();
		page = (page != null && page > 0) ? page : 1;
		PageQuery pageQuery = new PageQuery(page);
		args.put("start", pageQuery.getIndex());
		args.put("size", pageQuery.getSize());
		String sql = "SELECT * FROM article WHERE id IN (SELECT article_id FROM favorite WHERE user_id = :user_id ORDER BY gmt_created DESC LIMIT :start, :size )";
		List<ArticleDO> list = articleDAO.queryNative(sql, args);
		if (CollectionUtils.isEmpty(list)) {
			return Result.newError().with(ResultCode.Error_Fav_Article_Empty);
		}
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		for (ArticleDO article : list) {
			ArticleVO vo = ArticleVO.newInstance(article, true);
			if (vo == null) {
				continue;
			}
			articleList.add(vo);
		}
		return Result.newSuccess().with(ResultCode.Success).with("likeArticleList", articleList);
	}
}
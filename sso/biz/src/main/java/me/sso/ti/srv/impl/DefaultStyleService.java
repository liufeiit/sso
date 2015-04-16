package me.sso.ti.srv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.sso.ti.dataobject.StyleDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.CreateStyleRequest;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.ImageService;
import me.sso.ti.srv.StyleService;
import me.sso.ti.vo.StyleVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:06:23
 */
@Service("styleService")
public class DefaultStyleService extends BaseService implements StyleService {

	@Autowired
	private ImageService imageService;
	
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result createStyle(CreateStyleRequest request) {
		StyleDO style = new StyleDO();
		style.setDescription(request.getDescription());
		style.setName(request.getName());
		style.setStatus(StyleDO.Available);
		Date date = new Date();
		style.setGmt_created(date);
		style.setGmt_modified(date);
		Result uploadFloater = imageService.upload(request.getFloater());
		if(uploadFloater.isSuccess()) {
			style.setFloater((Long) uploadFloater.get("image_id"));
		}
		Result uploadBackground = imageService.upload(request.getBackground());
		if(uploadBackground.isSuccess()) {
			style.setBackground((Long) uploadBackground.get("image_id"));
		}
		Result uploadIcon = imageService.upload(request.getIcon());
		if(uploadIcon.isSuccess()) {
			style.setIcon((Long) uploadIcon.get("image_id"));
		}
		styleDAO.persist(style);
		return Result.newSuccess().with(ResultCode.Success).response(StyleVO.newInstance(style));
	}

	@Override
	public Result getStyleList() {
		List<StyleDO> styles = styleDAO.findAll();
		List<StyleVO> styleList = new ArrayList<StyleVO>();
		for (StyleDO style : styles) {
			StyleVO vo = StyleVO.newInstance(style);
			if(vo == null) {
				continue;
			}
			styleList.add(vo);
		}
		return Result.newSuccess().with(ResultCode.Success).with("styleList", styleList);
	}

	@Override
	public Result getStyle(Long id) {
		StyleDO style = styleDAO.get(id);
		StyleVO vo = StyleVO.newInstance(style);
		return Result.newSuccess().with(ResultCode.Success).with("style", vo);
	}
}